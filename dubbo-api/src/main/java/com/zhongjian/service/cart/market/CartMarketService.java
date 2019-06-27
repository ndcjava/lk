package com.zhongjian.service.cart.market;

import com.zhongjian.dto.cart.market.result.CartMarketListResultDTO;
import com.zhongjian.dto.common.ResultDTO;

import java.util.List;

/**
 * @Author: ldd
 */
public interface CartMarketService {

    List<CartMarketListResultDTO> queryList();
}
