package com.zhongjian;


import com.alibaba.fastjson.JSONObject;
import com.zhongjian.commoncomponent.PropUtil;
import com.zhongjian.dao.cart.CartParamDTO;
import com.zhongjian.dao.entity.cart.rider.CartRiderOrderBean;
import com.zhongjian.dao.entity.cart.user.UserBean;
import com.zhongjian.dao.framework.inf.HmDAO;
import com.zhongjian.dao.jdbctemplate.StoreAddressDao;
import com.zhongjian.dto.adv.result.CartAdvResultDTO;
import com.zhongjian.dto.cart.market.result.CartMarketListResultDTO;
import com.zhongjian.dto.common.ResultDTO;
import com.zhongjian.dto.cart.basket.query.CartBasketDelQueryDTO;
import com.zhongjian.dto.cart.basket.query.CartBasketEditQueryDTO;
import com.zhongjian.dto.cart.basket.query.CartBasketListQueryDTO;
import com.zhongjian.dto.cart.address.query.CartAddressQueryDTO;
import com.zhongjian.localservice.UserLocalService;
import com.zhongjian.service.address.AddressService;
import com.zhongjian.service.cart.adv.CartAdvService;
import com.zhongjian.service.cart.basket.CartBasketService;
import com.zhongjian.service.cart.market.CartMarketService;
import com.zhongjian.service.cart.shopown.CartShopownService;
import com.zhongjian.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring/dubbo-server.xml"})
public class AppTest {
    @Resource
    private CartBasketService hmBasketService;

    @Resource
    private CartShopownService cartShopownService;

    @Resource
    private CartAdvService cartAdvService;

    @Resource
    private CartMarketService cartMarketService;


    @Resource
    private AddressService addressService;


    @Autowired
    private StoreAddressDao storeAddressDao;

    @Resource
    private UserLocalService userLocalService;

    private HmDAO<CartRiderOrderBean, Integer> hmDAO;


    @Resource
    public void setHmDAO(HmDAO<CartRiderOrderBean, Integer> hmDAO) {
        this.hmDAO = hmDAO;
        this.hmDAO.setPerfix(CartRiderOrderBean.class.getName());
    }


    @Resource
    private PropUtil propUtil;


    @Test
    public void addOrUpdateInfo() {
        CartBasketEditQueryDTO hmBasketDelQueryDTO = new CartBasketEditQueryDTO();
        hmBasketDelQueryDTO.setUid(26);
        hmBasketDelQueryDTO.setGid(1130);
        hmBasketDelQueryDTO.setSid(127);
        hmBasketDelQueryDTO.setSpecid(2);
//        hmBasketDelQueryDTO.setPrice("20");
        hmBasketDelQueryDTO.setAmount("2");
        hmBasketDelQueryDTO.setRemark("2");
        System.out.println(JSONObject.toJSONString(hmBasketService.addOrUpdateInfo(hmBasketDelQueryDTO)));

    }

    @Test
    public void queryList() {
        CartBasketListQueryDTO cartBasketListQueryDTO = new CartBasketListQueryDTO();
        cartBasketListQueryDTO.setUid(26);
        cartBasketListQueryDTO.setSid(106);
        ResultDTO<Object> objectResultDTO = hmBasketService.queryList(cartBasketListQueryDTO);
        System.out.println(JSONObject.toJSONString(objectResultDTO));

    }

    @Test
    public void deleteInfoById() {
        CartBasketDelQueryDTO hmBasketListQueryDTO = new CartBasketDelQueryDTO();
        hmBasketListQueryDTO.setId(36);
        hmBasketListQueryDTO.setUid(32716);
        System.out.println(JSONObject.toJSONString(hmBasketService.deleteInfoById(hmBasketListQueryDTO)));
    }

    @Test
    public void deleteAllInfoById() {
        CartBasketDelQueryDTO hmBasketListQueryDTO = new CartBasketDelQueryDTO();
        hmBasketListQueryDTO.setSid(1);
        hmBasketListQueryDTO.setUid(1);
        System.out.println(JSONObject.toJSONString(hmBasketService.deleteAllInfoById(hmBasketListQueryDTO)));
    }

    @Test
    public void editInfo() {
        CartBasketEditQueryDTO hmBasketListQueryDTO = new CartBasketEditQueryDTO();
        hmBasketListQueryDTO.setAmount("0");
//       hmBasketListQueryDTO.setPrice("100");
        hmBasketListQueryDTO.setId(33);
        hmBasketListQueryDTO.setUid(32716);
        System.out.println(JSONObject.toJSONString(hmBasketService.editInfo(hmBasketListQueryDTO)));
    }

    @Test
    public void queryList1() {
        System.out.println(JSONObject.toJSONString(cartShopownService.queryList(32716)));
    }


    @Test
    public void order() {
        CartParamDTO cartParamDTO = new CartParamDTO();
        Long todayZeroTime = DateUtil.getTodayZeroTime();
        cartParamDTO.setCtime(todayZeroTime.intValue());
        cartParamDTO.setUid(32716);
        Integer findCountByUid = this.hmDAO.executeSelectOneMethod(cartParamDTO, "findCountByUid", Integer.class);
        System.out.println(findCountByUid);
    }


    @Test
    public void address() {
        CartAddressQueryDTO cartAddressQueryDTO = new CartAddressQueryDTO();
        cartAddressQueryDTO.setId(1);
        cartAddressQueryDTO.setUid(1);


        System.out.println(addressService.previewOrderAddress(cartAddressQueryDTO));
    }

    @Test
    public void add() {
        CartAddressQueryDTO cartAddressQueryDTO = new CartAddressQueryDTO();
        cartAddressQueryDTO.setId(1);
        cartAddressQueryDTO.setUid(1);


        System.out.println(addressService.previewOrderAddress(cartAddressQueryDTO));
    }

    @Test
    public void updateDefalut() {
        CartAddressQueryDTO cartAddressQueryDTO = new CartAddressQueryDTO();
        cartAddressQueryDTO.setId(1);
        cartAddressQueryDTO.setUid(1);


        addressService.updateDefaultAddress(cartAddressQueryDTO);
    }

    @Test
    public void updateUserMarketId() {
        Map<String, Object> map = new HashMap<>();
        List<CartAdvResultDTO> cartAdvResultDTOS = cartAdvService.queryList();
        List<CartMarketListResultDTO> cartMarketListResultDTOS = cartMarketService.queryList();
        map.put("adv", cartAdvResultDTOS);
        map.put("market", cartMarketListResultDTOS);
        System.out.println(JSONObject.toJSONString(map));
    }


    @Test
    public void storeAddressDao() {

        UserBean userBean = new UserBean();
        userBean.setUnionid("3242123321");
        userBean.setAppletsOpenid("3242123321");
        userBean.setUserType(0);
        userBean.setIsInside(0);
        Byte status = 1;
        userBean.setStatus(status);
        userBean.setCtime(DateUtil.getCurrentTime());
        String loginToken = UUID.randomUUID().toString().replaceAll("\\-", "");
        userBean.setLoginToken(loginToken);
        userBean.setIntegral(0);
        userBean.setPic("dfaf");
        userBean.setNick("dfaf");
        userBean.setPrizetimes(0);
        userBean.setVipStatus(0);
        userBean.setVipLevel(0);
        userBean.setVipPromotionReward(new BigDecimal(0.00));
        userBean.setVipPromotionRewardget(new BigDecimal(0.00));
        userBean.setVipPromotionRewardover(new BigDecimal(0.00));
        Integer addTag = userLocalService.add(userBean);
        System.out.println(addTag);
    }

    @Test
    public void findGoodsSpecByGid(){
        System.out.println(JSONObject.toJSONString(hmBasketService.findGoodsSpecByGid(1914)));
    }

}
