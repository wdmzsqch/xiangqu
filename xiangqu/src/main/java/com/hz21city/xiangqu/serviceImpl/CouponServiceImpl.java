package com.hz21city.xiangqu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.dao.IUserCouponDao;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;
import com.hz21city.xiangqu.service.ICouponService;

@Service("couponService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CouponServiceImpl implements ICouponService {
	@Resource
	private ICouponInfoDao couponInfoDao;
	@Resource
	private IUserCouponDao userCouponDao;
	
	@Resource
	private IShopInfoDao shopInfoDao;
	
	@Resource
	private IAreaDao areaDao;
	
	@Override
	public List<ShopInfo> getShopCouponList() {
		List<Long> ShopIds  = couponInfoDao.getStoreIdByCoupon();
		List<ShopInfo> shoplist = new ArrayList<ShopInfo>();
		for (Long shopid : ShopIds) {
			if (shopid != null && shopid > 0) {
				ShopInfo shopinfo = shopInfoDao.selectByPrimaryKey(shopid);
				if (shopinfo != null) {
					List<CouponInfo> couponlist = couponInfoDao.getCouponListByStore(shopinfo.getId());
					shopinfo.setCouponlist(couponlist);
					shoplist.add(shopinfo);
				}
			}
		}
		return shoplist;
	}

	@Override
	public CouponInfo getCouponInfoById(long id) {
		CouponInfo couponinfo = couponInfoDao.selectByPrimaryKey(id);
		return couponinfo;
	}

	@Override
	public ShopInfo getShopInfoById(long id) {
		ShopInfo shopinfo = shopInfoDao.selectByPrimaryKey(id);
		if(shopinfo.getProvince() != null && shopinfo.getProvince() != 0){
			shopinfo.setProname(areaDao.selectByPrimaryKey(shopinfo.getProvince()).getName());
		}
		if(shopinfo.getCity() != null && shopinfo.getCity() != 0){
			shopinfo.setCityname(areaDao.selectByPrimaryKey(shopinfo.getCity()).getName());
		}
		if(shopinfo.getArea() != null && shopinfo.getArea() != 0){
			shopinfo.setAreaname(areaDao.selectByPrimaryKey(shopinfo.getArea()).getName());
		}
		return shopinfo;
	}

	@Override
	public List<UserCoupon> getMyCoupon(long userid) {
		return userCouponDao.getMyCoupon(userid);
	}

	@Override
	public int userCouponCount(long userid,long coupon_id) {
		// TODO Auto-generated method stub
		int usercoupon = userCouponDao.userCouponCount(userid, coupon_id);
		return usercoupon;
	}

	@Override
	public int  getCouponByCode(String code) {
		int codecoupon = userCouponDao.getUserCouponByCode(code);
		return codecoupon;
	}

	@Override
	public void addUserCoupon(UserCoupon usercoupon) {
		userCouponDao.insertSelective(usercoupon);
	}

	@Override
	public void updateCoupon(CouponInfo coupon) {
		couponInfoDao.updateByPrimaryKeySelective(coupon);
	}

	@Override
	public UserCoupon getUserCouponById(long usercoupon_id) {
		UserCoupon usercoupon = userCouponDao.selectByPrimaryKey(usercoupon_id);
		if(usercoupon!=null){
			CouponInfo coupon = couponInfoDao.selectByPrimaryKey(usercoupon.getCouponId());
			usercoupon.setValidity(coupon.getValidity());
			usercoupon.setIntro(coupon.getIntro());
			usercoupon.setTitle(coupon.getTitle());
			usercoupon.setMoney(coupon.getMoney());
			usercoupon.setType(coupon.getType());
			ShopInfo shop = shopInfoDao.selectByPrimaryKey(coupon.getStoreId());
			if(shop.getProvince() != null && shop.getProvince() != 0){
				shop.setProname(areaDao.selectByPrimaryKey(shop.getProvince()).getName());
			}
			if(shop.getCity() != null && shop.getCity() != 0){
				shop.setCityname(areaDao.selectByPrimaryKey(shop.getCity()).getName());
			}
			if(shop.getArea() != null && shop.getArea() != 0){
				shop.setAreaname(areaDao.selectByPrimaryKey(shop.getArea()).getName());
			}
			usercoupon.setShop_name(shop.getCompanyName());
			usercoupon.setShop_pic(shop.getPic());
			usercoupon.setShop_phone(shop.getPhone());
			usercoupon.setShop_address(shop.getProname()+shop.getCityname()+shop.getAreaname()+shop.getAddress());
		}
		return usercoupon;
	}

	@Override
	public List<ShopInfo> getSearchShopCouponList(String type,Integer datetype,String shops) {
		List<Long> ShopIds  = new ArrayList<Long>();
		if(CommonUtils.isEmptyString(shops)){
			ShopIds = couponInfoDao.getStoreIdByCoupon();
		}else{
			String[] shopids = shops.split(",");
			for(String shopid:shopids){
				ShopIds.add(CommonUtils.parseLong(shopid, 0));
			}
		}
		List<ShopInfo> shoplist = new ArrayList<ShopInfo>();
		for (Long shopid : ShopIds) {
			if (shopid != null && shopid > 0) {
				ShopInfo shopinfo = shopInfoDao.selectByPrimaryKey(shopid);
				if (shopinfo != null) {
					List<CouponInfo> couponlist = couponInfoDao.getSearchCouponListByStore(shopinfo.getId(),datetype,type);
					shopinfo.setCouponlist(couponlist);
					shoplist.add(shopinfo);
				}
			}
		}
		return shoplist;
	}

	@Override
	public List<UserCoupon> getMyCouponSearch(Long userid, String type, Integer datetype, Long[] shops) {
		List<UserCoupon> usercouponlist = new ArrayList<UserCoupon>();
		if(shops.length == 0){
			usercouponlist = userCouponDao.getMyCouponSearch(userid, type, datetype, null);
		}else{
			usercouponlist = userCouponDao.getMyCouponSearch(userid, type, datetype, shops);
		}
		return usercouponlist;
	}

	@Override
	public CouponInfo getCouponInfoByCode(String code) {
		CouponInfo coupon = couponInfoDao.CouponInfoByCode(code);
		return coupon;
	}

}
