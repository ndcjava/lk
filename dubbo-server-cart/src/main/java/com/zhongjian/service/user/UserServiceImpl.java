package com.zhongjian.service.user;

import com.alibaba.fastjson.JSONObject;
import com.zhongjian.commoncomponent.PropUtil;
import com.zhongjian.dao.entity.cart.user.UserBean;
import com.zhongjian.dao.framework.impl.HmBaseService;
import com.zhongjian.dao.jdbctemplate.CouponDao;
import com.zhongjian.dto.message.result.MessageResParamDTO;
import com.zhongjian.dto.user.query.UserQueryDTO;
import com.zhongjian.dto.user.result.UserCopResultDTO;
import com.zhongjian.dto.user.result.UserResultDTO;
import com.zhongjian.util.HttpConnectionPoolUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: ldd
 */
@Service("userService")
public class UserServiceImpl extends HmBaseService<UserBean, Integer> implements UserService {

	@Autowired
	private CouponDao couponDao;

	@Autowired
	private PropUtil propUtil;
	
	@Override
	public Integer getUidByLoginToken(String loginToken) {

		Integer findUserByLoginToken = this.dao.executeSelectOneMethod(loginToken, "findUserByLoginToken",
				Integer.class);

		if (null == findUserByLoginToken) {
			return 0;
		}
		return findUserByLoginToken;
	}

	@Override
	public UserResultDTO getUserBeanById(Integer id) {

		UserResultDTO findUserById = this.dao.executeSelectOneMethod(id, "findUserById", UserResultDTO.class);

		return findUserById;
	}

	@Override
	public List<UserCopResultDTO> getCouponByUid(UserQueryDTO userQueryDTO) {

		List<UserCopResultDTO> findCouponByUid = this.dao.executeListMethod(userQueryDTO.getUid(),
				"findCouponByUidModelOne", UserCopResultDTO.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Iterator<UserCopResultDTO> iterator = findCouponByUid.iterator();
		while (iterator.hasNext()) {
			UserCopResultDTO userCopResultDTO = iterator.next();
			Map<String, Object> coupInfo = couponDao.getCoupInfo(userCopResultDTO.getMongoId());
			Integer type = (Integer) coupInfo.get("type");
			// 是否达到满减
			// state设置
			if ((new BigDecimal(userQueryDTO.getPrice()).compareTo((BigDecimal) coupInfo.get("pay_full")) >= 0
					&& type == 0) || type == 1) {
				userCopResultDTO.setState(0);
				userCopResultDTO.setReason("");
			} else {
				userCopResultDTO.setState(1);
				userCopResultDTO.setReason("未达到满减");
			}
			// type设置
			userCopResultDTO.setType(type);
			// content设置
			if (type == 0) {
				userCopResultDTO.setContent("满" + coupInfo.get("pay_full") + "元可用(每天限用一张)");
			} else {
				userCopResultDTO.setContent("抵消该单配送费(每天限用一张)");
			}

			String startTime = sdf.format(new Date((long) userCopResultDTO.getStarttime() * 1000));
			String endTime = sdf.format(new Date((long) userCopResultDTO.getEndtime() * 1000));
			userCopResultDTO.setEndtime(null);
			userCopResultDTO.setStarttime(null);
			userCopResultDTO.setMongoId(null);
			// time设置
			userCopResultDTO.setStart_time(startTime);
			userCopResultDTO.setEnd_time(endTime);

			// 去掉不符合市场的
			List<Integer> mids = (List<Integer>) coupInfo.get("marketid");
			Boolean flag = true;
			if (mids != null) {
				for (Integer mid : mids) {
					if (userQueryDTO.getMarketId().equals(mid)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					iterator.remove();
				}
			} else {
				continue;
			}
		}
		return findCouponByUid;
	}

	@Override
	public List<UserCopResultDTO> getCVCouponByUid(UserQueryDTO userQueryDTO) {

		List<UserCopResultDTO> findCouponByUid = this.dao.executeListMethod(userQueryDTO.getUid(),
				"findCouponByUidModelTWO", UserCopResultDTO.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Iterator<UserCopResultDTO> iterator = findCouponByUid.iterator();
		while (iterator.hasNext()) {
			UserCopResultDTO userCopResultDTO = iterator.next();
			Map<String, Object> coupInfo = couponDao.getCoupInfo(userCopResultDTO.getMongoId());
			Integer type = (Integer) coupInfo.get("type");
			// 是否达到满减
			// state设置
			if ((new BigDecimal(userQueryDTO.getPrice()).compareTo((BigDecimal) coupInfo.get("pay_full")) >= 0
					&& type == 0)) {
				userCopResultDTO.setState(0);
				userCopResultDTO.setReason("");
			} else {
				userCopResultDTO.setState(1);
				userCopResultDTO.setReason("未达到满减");
			}
			// type设置
			userCopResultDTO.setType(type);
			// content设置
			if (type == 0) {
				userCopResultDTO.setContent("满" + coupInfo.get("pay_full") + "元可用(每天限用一张)");
			}
			String startTime = sdf.format(new Date((long) userCopResultDTO.getStarttime() * 1000));
			String endTime = sdf.format(new Date((long) userCopResultDTO.getEndtime() * 1000));
			userCopResultDTO.setEndtime(null);
			userCopResultDTO.setStarttime(null);
			userCopResultDTO.setMongoId(null);
			// time设置
			userCopResultDTO.setStart_time(startTime);
			userCopResultDTO.setEnd_time(endTime);
		}
		return findCouponByUid;

	}

	@Override
	public Map<String, Object> wxAppletLogin(String code, String headPic, String nickName) {
		String url = "https://api.weixin.qq.com/sns/jscode2session";          
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("appid", propUtil.getWxAppLetsId());
		params.put("secret", propUtil.getWxAppletsSecret());
		params.put("js_code", code);
		params.put("grant_type", "authorization_code");
		String data = HttpConnectionPoolUtil.get(url, params, null, "utf-8");
		WxLoginInfo wxLoginInfo = JSONObject.parseObject(data, WxLoginInfo.class);
		String unionid = wxLoginInfo.getUnionid();
		String openid = wxLoginInfo.getOpenid();
		return null;
	}

	@Override
	public Map<String, Object> wxAppletLogin(String code, String headPic, String nickName, Integer shareId) {
		String url = "https://api.weixin.qq.com/sns/jscode2session";          
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("appid", propUtil.getWxAppLetsId());
		params.put("secret", propUtil.getWxAppletsSecret());
		params.put("js_code", code);
		params.put("grant_type", "authorization_code");
		String data = HttpConnectionPoolUtil.get(url, params, null, "utf-8");
		WxLoginInfo wxLoginInfo = JSONObject.parseObject(data, WxLoginInfo.class);
		String unionid = wxLoginInfo.getUnionid();
		String openid = wxLoginInfo.getOpenid();
		return null;
	}
	
	static class WxLoginInfo{
		private String openid;
		private String session_key;
		private String unionid;
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getSession_key() {
			return session_key;
		}
		public void setSession_key(String session_key) {
			this.session_key = session_key;
		}
		public String getUnionid() {
			return unionid;
		}
		public void setUnionid(String unionid) {
			this.unionid = unionid;
		}
		
	}
}
