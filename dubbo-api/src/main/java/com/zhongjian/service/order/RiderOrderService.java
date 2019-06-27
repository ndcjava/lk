package com.zhongjian.service.order;

import java.util.List;
import java.util.Map;

public interface RiderOrderService {

	void takeOrder(Integer rid,Integer orderid);
	
	//order->rid != fromRiderId(fail) ,else create record（orderId唯一否则提示转单已发出）
	Integer createOrderSwap(Integer fromRiderId,Integer orderId,Integer rid);
	
	//0->rid , originRid->rid,rid->rid(fail) (次数的加减)
	void OrderSwap(Integer orderId,Integer rid);
	
	//originRid->rid(次数的加减) 加zookeeper锁判断OrderSwapId是否存在
	void AcceptOrRejectOrder(Integer OrderSwapId,Integer accept);
	
	List<Map<String, Object>> getRidersOfMarket(Integer rid);
	
	//骑手获取转单请求列表
}
