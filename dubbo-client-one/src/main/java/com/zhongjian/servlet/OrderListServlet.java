package com.zhongjian.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhongjian.dto.Page;
import com.zhongjian.dto.common.CommonMessageEnum;
import com.zhongjian.dto.common.ResultUtil;
import com.zhongjian.dto.order.order.query.OrderQueryDTO;

import org.apache.log4j.Logger;

import com.zhongjian.common.FormDataUtil;
import com.zhongjian.common.GsonUtil;
import com.zhongjian.common.ResponseHandle;
import com.zhongjian.common.SpringContextHolder;
import com.zhongjian.executor.ThreadPoolExecutorSingle;
import com.zhongjian.service.order.OrderDetailsService;

import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/v1/order/orderlist", asyncSupported = true)
public class OrderListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(OrderListServlet.class);

	private OrderDetailsService orderDetailsService = (OrderDetailsService) SpringContextHolder.getBean(OrderDetailsService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> formData = FormDataUtil.getFormData(request);
		AsyncContext asyncContext = request.startAsync();
		ServletInputStream inputStream = request.getInputStream();
		
		inputStream.setReadListener(new ReadListener() {
			@Override
			public void onDataAvailable() throws IOException {
			}

			@Override
			public void onAllDataRead() {
				ThreadPoolExecutorSingle.executor.execute(() -> {
					String result = GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.SERVERERR));
					try {
					Integer uid = (Integer) request.getAttribute("uid");
					Integer status = Integer.valueOf(formData.get("status"));
					Integer page = Integer.valueOf(formData.get("page"));
					Integer pageSize = Integer.valueOf(formData.get("pagesize"));
					result = OrderListServlet.this.handle(uid, status,page,pageSize);
					// 返回数据
					
						ResponseHandle.wrappedResponse(asyncContext.getResponse(), result);
					} catch (Exception e) {
						try {
							ResponseHandle.wrappedResponse(asyncContext.getResponse(), result);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						log.error("fail order/orderlist : " + e.getMessage());
					}
					asyncContext.complete();
				});
			}

			@Override
			public void onError(Throwable t) {
				asyncContext.complete();
			}
		});

	}

	private String handle(Integer uid, Integer status,Integer currentPage,Integer pageSize) {
		if (uid == 0) {
			return GsonUtil.GsonString(ResultUtil.getFail(CommonMessageEnum.USER_IS_NULL));
		}
		OrderQueryDTO orderQueryDTO = new OrderQueryDTO();
		orderQueryDTO.setUid(uid);
		orderQueryDTO.setStatus(status);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		return GsonUtil.GsonString(orderDetailsService.queryList(orderQueryDTO, page));
	}
}