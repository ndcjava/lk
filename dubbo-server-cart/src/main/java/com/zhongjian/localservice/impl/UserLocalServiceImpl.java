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

	@Autowired
	private DistributedLock zkLock;

	@Override
	public Integer add(UserBean user) {
		if (StringUtil.isNotBlank(user.getUnionid())) {
			String lockName = null;
			try {
				// zookeeper加锁(针对uid加锁)
				lockName = zkLock.lock(user.getUnionid());
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
					return findUserByUnionId.getId();
				}
			} catch (Exception e) {
				return 0;
			} finally {
				try {
					zkLock.unlock(lockName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			return 0;
		}

	}

}
