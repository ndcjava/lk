package com.zhongjian.service.cart.adv.impl;

import com.zhongjian.dao.entity.cart.adv.CartAdvBean;
import com.zhongjian.dao.framework.impl.HmBaseService;
import com.zhongjian.dto.adv.result.CartAdvResultDTO;
import com.zhongjian.dto.common.ResultDTO;
import com.zhongjian.dto.common.ResultUtil;
import com.zhongjian.service.cart.adv.CartAdvService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ldd
 */
@Service
public class CartAdvServiceImpl extends HmBaseService<CartAdvBean, Integer> implements CartAdvService{

    @Override
    public ResultDTO<Object> queryList() {

        List<CartAdvResultDTO> queryList = this.dao.executeListMethod(null, "queryList", CartAdvResultDTO.class);

        return ResultUtil.getSuccess(queryList);
    }
}