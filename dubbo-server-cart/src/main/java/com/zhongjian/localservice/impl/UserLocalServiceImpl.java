package com.zhongjian.localservice.impl;

import com.zhongjian.dao.entity.cart.user.UserBean;
import com.zhongjian.dao.framework.impl.HmBaseService;
import com.zhongjian.localservice.UserLocalService;
import com.zhongjian.util.DistributedLock;
import com.zhongjian.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLocalServiceImpl extends HmBaseService<UserBean, Integer> implements UserLocalService {


	@Override
	public Integer add(UserBean user) {
		if (StringUtil.isNotBlank(user.getUnionid())) {
			try {
				UserBean findUserByUnionId = this.dao.executeSelectOneMethod(user.getUnionid(), "findUserByUnionId",
						UserBean.class);
				if (null == findUserByUnionId) {
					int i = this.dao.insertSelective(user);
					if (i > 0) {
						return user.getId();
					} else {
						return 0;
					}
				} else {
					//更新login_token
					Integer id = findUserByUnionId.getId();
					UserBean updateUserTokenBean = new UserBean();
					updateUserTokenBean.setLoginToken(user.getLoginToken());
					updateUserTokenBean.setId(id);
					this.dao.updateByPrimaryKeySelective(updateUserTokenBean);
					return id;
				}
			} catch (Exception e) {
				return 0;
			} 
		} else {
			return 0;
		}

	}

}
