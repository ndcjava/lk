package com.zhongjian.service.user;

import com.zhongjian.dto.user.query.UserQueryDTO;
import com.zhongjian.dto.user.result.UserCopResultDTO;
import com.zhongjian.dto.user.result.UserResultDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
	/**
	 * 根据token获取uid
	 * @param loginToken
	 * @return
	 */
	Integer getUidByLoginToken(String loginToken);

	/**
	 * 根据uid获取user信息
	 * @param id
	 * @return
	 */
	UserResultDTO getUserBeanById(Integer id);


	/**
	 * 根据uid查询优惠卷信息.
	 * @param userQueryDTO
	 * @return
	 */
	List<UserCopResultDTO> getCouponByUid(UserQueryDTO userQueryDTO);


	/**
	 * 根据uid查询优惠卷信息.
	 * @param userQueryDTO
	 * @return
	 */
	List<UserCopResultDTO> getCVCouponByUid(UserQueryDTO userQueryDTO);
	
	/**
	 * 微信小程序登录
	 * @param code
	 * @param headPic
	 * @param nickName
	 * @return Map {"id":1,"login_token":""}
	 */
	Map<String,Object> wxAppletLogin(String code,String headPic,String nickName);

	/**
	 * 微信小程序登录
	 * @param code
	 * @param headPic
	 * @param nickName
	 * @param shareId
	 * @return Map {"id":1,"login_token":""}
	 */
	Map<String,Object> wxAppletLogin(String code,String headPic,String nickName,Integer shareId);
}
