package com.zhongjian.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

//jvm http connetions for single url 
public class HttpConnectionPoolUtil {

	private static final int CONNECT_TIMEOUT = 10000;// 设置连接建立的超时时间为10s
	private static final int CONNECTION_REQUEST_TIMEOUT = 10000;
	private static final int SOCKET_TIMEOUT = 10000;
	private static final int MAX_CONN = 50; // 最大连接数
	private static final int Max_PRE_ROUTE = 25;
	private static final int MAX_ROUTE = 40;
	private static CloseableHttpClient httpClient; // 发送请求的客户端单例
	private static PoolingHttpClientConnectionManager manager; // 连接池管理类
	private static ScheduledExecutorService monitorExecutor;

	private final static Object syncLock = new Object(); // 相当于线程锁,用于线程安全

	/**
	 * 对http请求进行基本设置
	 * 
	 * @param httpRequestBase http请求
	 */
	private static void setRequestConfig(HttpRequestBase httpRequestBase) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
				.setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

		httpRequestBase.setConfig(requestConfig);
	}

	public static CloseableHttpClient getHttpClient(String url) {

		String hostName = url.split("/")[2];
		int port = 80;
		if (hostName.contains(":")) {
			String[] args = hostName.split(":");
			hostName = args[0];
			port = Integer.parseInt(args[1]);
		}

		if (httpClient == null) {
			// 多线程下多个线程同时调用getHttpClient容易导致重复创建httpClient对象的问题,所以加上了同步锁
			synchronized (syncLock) {
				if (httpClient == null) {
					httpClient = createHttpClient(hostName, port);
					// 开启监控线程,对异常和空闲线程进行关闭
					monitorExecutor = Executors.newScheduledThreadPool(1);
					monitorExecutor.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							// 关闭异常连接
							manager.closeExpiredConnections();
							// 关闭5s空闲的连接
							manager.closeIdleConnections(30000, TimeUnit.MILLISECONDS);
						}
					}, 30000, 30000, TimeUnit.MILLISECONDS);
				}
			}
		}
		return httpClient;
	}

	/**
	 * 根据host和port构建httpclient实例
	 * 
	 * @param host 要访问的域名
	 * @param port 要访问的端口
	 * @return
	 */
	public static CloseableHttpClient createHttpClient(String host, int port) {
		ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", plainSocketFactory).register("https", sslSocketFactory).build();

		manager = new PoolingHttpClientConnectionManager(registry);
		// 设置连接参数
		manager.setMaxTotal(MAX_CONN); // 最大连接数
		manager.setDefaultMaxPerRoute(Max_PRE_ROUTE); // 路由最大连接数

		HttpHost httpHost = new HttpHost(host, port);
		manager.setMaxPerRoute(new HttpRoute(httpHost), MAX_ROUTE);

		// 请求失败时,进行请求重试
		HttpRequestRetryHandler handler = new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
				if (i > 3) {
					// 重试超过3次,放弃请求
					return false;
				}
				if (e instanceof NoHttpResponseException) {
					// 服务器没有响应,可能是服务器断开了连接,应该重试
					return true;
				}
				if (e instanceof SSLHandshakeException) {
					// SSL握手异常
					return false;
				}
				if (e instanceof InterruptedIOException) {
					// 超时
					return false;
				}
				if (e instanceof UnknownHostException) {
					// 服务器不可达
					return false;
				}
				if (e instanceof ConnectTimeoutException) {
					// 连接超时
					return false;
				}
				if (e instanceof SSLException) {
					return false;
				}

				HttpClientContext context = HttpClientContext.adapt(httpContext);
				HttpRequest request = context.getRequest();
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					// 如果请求不是关闭连接的请求
					return true;
				}
				return false;
			}
		};
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler)
				.build();
		return client;
	}

	/**
	 * 设置post请求的参数
	 * 
	 * @param httpPost
	 * @param params
	 */
	private static void setPostParams(HttpPost httpPost, Map<String, String> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keys = params.keySet();
		for (String key : keys) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	  /**
     * Get请求方式
     *
     * @param url
     *            请求URL
     * @param params
     *            参数
     * @param contentType
     *            格式
     * @param userAgent
     *            UA
     * @param encoding
     *            编码
     * @return
     */
    public static String get(String url, Map<String, String> params, String contentType, String encoding) {
        String data = "";
        HttpGet httpGet = new HttpGet();
        CloseableHttpResponse response = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            boolean first = true;
            if (params != null) {
                for (Entry<String, String> entry : params.entrySet()) {
                    if (first && !url.contains("?")) {
                        sb.append("?");
                    } else {
                        sb.append("&");
                    }
                    sb.append(entry.getKey());
                    sb.append("=");
                    String value = entry.getValue();
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                    first = false;
                }
            }

            // LOG.info("[HttpPoolUtils Get] begin invoke:" + sb.toString());
            httpGet.setURI(new URI(sb.toString()));
            setRequestConfig(httpGet);
            if (contentType != null && contentType != "") {
                httpGet.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
            } else {
                httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
            }
                httpGet.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");

            response = getHttpClient(url).execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                data = EntityUtils.toString(entity, encoding);
            }
        } catch (Exception e) {
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpGet.reset();
        }
        return data;
    }
	public static String post(String url, Map<String, String> params) {
		HttpPost httpPost = new HttpPost(url);
		setRequestConfig(httpPost);
		setPostParams(httpPost, params);
		CloseableHttpResponse response = null;
		InputStream in = null;
		String result = null;
		try {
			response = getHttpClient(url).execute(httpPost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				in = entity.getContent();
				result = IOUtils.toString(in, "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String post(String url, HttpPost httpPost, Map<String, String> params) {
		setRequestConfig(httpPost);
		setPostParams(httpPost, params);
		CloseableHttpResponse response = null;
		InputStream in = null;
		String result = null;
		try {
			response = getHttpClient(url).execute(httpPost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				in = entity.getContent();
				result = IOUtils.toString(in, "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 关闭连接池
	 */
	public static void closeConnectionPool() {
		try {
			httpClient.close();
			manager.close();
			monitorExecutor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}