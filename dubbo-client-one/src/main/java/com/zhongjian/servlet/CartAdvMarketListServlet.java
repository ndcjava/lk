package com.zhongjian.servlet;

import com.zhongjian.common.FormDataUtil;
import com.zhongjian.common.GsonUtil;
import com.zhongjian.common.ResponseHandle;
import com.zhongjian.common.SpringContextHolder;
import com.zhongjian.dto.adv.result.CartAdvResultDTO;
import com.zhongjian.dto.cart.market.result.CartMarketListResultDTO;
import com.zhongjian.dto.common.CommonMessageEnum;
import com.zhongjian.dto.common.ResultDTO;
import com.zhongjian.dto.common.ResultUtil;
import com.zhongjian.executor.ThreadPoolExecutorSingle;
import com.zhongjian.service.cart.adv.CartAdvService;
import com.zhongjian.service.cart.market.CartMarketService;
import org.apache.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ldd
 */
@WebServlet(value = "/v1/home/init", asyncSupported = true)
public class CartAdvMarketListServlet {

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(CartAdvMarketListServlet.class);

    private CartAdvService cartAdvService = (CartAdvService) SpringContextHolder.getBean(CartAdvService.class);

    private CartMarketService cartMarketService = (CartMarketService) SpringContextHolder.getBean(CartMarketService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                        result = CartAdvMarketListServlet.this.handle();
                        // 返回数据

                        ResponseHandle.wrappedResponse(asyncContext.getResponse(), result);
                    } catch (Exception e) {
                        try {
                            ResponseHandle.wrappedResponse(asyncContext.getResponse(), result);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        log.error("fail home/init : " + e.getMessage());
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

    private String handle() {
        Map<String, Object> map = new HashMap<>();
        List<CartAdvResultDTO> cartAdvResultDTOS = cartAdvService.queryList();
        List<CartMarketListResultDTO> cartMarketListResultDTOS = cartMarketService.queryList();
        map.put("adv", cartAdvResultDTOS);
        map.put("market", cartMarketListResultDTOS);
        return GsonUtil.GsonString(ResultUtil.getSuccess(map));
    }
}
