package com.zhongjian.service.cart.market.impl;

import com.zhongjian.dao.entity.cart.market.CartMarketBean;
import com.zhongjian.dao.framework.impl.HmBaseService;
import com.zhongjian.dto.cart.market.result.CartMarketListResultDTO;
import com.zhongjian.dto.common.ResultDTO;
import com.zhongjian.dto.common.ResultUtil;
import com.zhongjian.service.cart.adv.CartAdvService;
import com.zhongjian.service.cart.market.CartMarketService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ldd
 */
@Service
public class CartMarketServiceImpl extends HmBaseService<CartMarketBean, Integer> implements CartMarketService {


    @Override
    public ResultDTO<Object> queryList() {

        List<CartMarketListResultDTO> findMarketList = this.dao.executeListMethod(null, "findMarketList", CartMarketListResultDTO.class);

        return ResultUtil.getSuccess(findMarketList);
    }
}
