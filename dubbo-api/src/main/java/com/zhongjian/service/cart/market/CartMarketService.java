package com.zhongjian.service.cart.market;

import com.zhongjian.dto.cart.market.result.CartMarketListResultDTO;
import com.zhongjian.dto.common.ResultDTO;

/**
 * @Author: ldd
 */
public interface CartMarketService {

    ResultDTO<Object> queryList();
}
