package com.zhongjian.service.cart.adv;

import com.zhongjian.dto.adv.result.CartAdvResultDTO;
import com.zhongjian.dto.common.ResultDTO;

import java.util.List;

/**
 * @Author: ldd
 */
public interface CartAdvService {


    /**
     * 查询列表
     */
    List<CartAdvResultDTO> queryList();


}
