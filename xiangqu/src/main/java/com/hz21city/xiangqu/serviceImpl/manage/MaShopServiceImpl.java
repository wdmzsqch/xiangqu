package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.dao.IStoreCotegoryDao;
import com.hz21city.xiangqu.dao.IndustryDao;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.StoreCotegory;
import com.hz21city.xiangqu.service.manage.IMaShopService;

@Service("maShopService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaShopServiceImpl implements IMaShopService {
	@Resource
	private IShopInfoDao shopInfoDao;
	
	@Resource
	private IAreaDao areaDao;
	
	@Resource
	private IndustryDao industryDao;
	
	@Resource
	private IStoreCotegoryDao storeCotegoryDao;

	@Override
	public List<ShopInfo> getShopList(String keywords, Integer page, Long industry_id, Long province, Long city, Long area) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		return shopInfoDao.getShopList(keywords, page, industry_id, province, city, area);
	}

	@Override
	public int getShopListSize(String keywords, Long industry_id, Long province, Long city, Long area) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		int total = shopInfoDao.getShopListSize(keywords, industry_id, province, city, area);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public ShopInfo getShopByid(Long id) {
		return shopInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void editShop(ShopInfo shopInfo) {
		if (shopInfo.getId() != null && shopInfo.getId() > 0) {
			shopInfoDao.updateByPrimaryKeySelective(shopInfo);
		} else {
			shopInfoDao.insertSelective(shopInfo);
		}
	}

	@Override
	public void delShop(Long id) {
		ShopInfo shopInfo = shopInfoDao.selectByPrimaryKey(id);
		shopInfo.setDelFlg(1);
		shopInfoDao.updateByPrimaryKeySelective(shopInfo);
	}

	@Override
	public List<Area> getAreaListByParentid(Long parentid) {
		List<Area> list = areaDao.getAreaListByParentid(parentid);
		return list;
	}

	@Override
	public Area getArea(Long id) {
		Area area = areaDao.selectByPrimaryKey(id);
		return area;
	}

	@Override
	public List<Industry> getIndustryList() {
		List<Industry> list = industryDao.getAllIndustry();
		return list;
	}

	@Override
	public List<ShopInfo> getAllShopList() {
		return shopInfoDao.getAllShopList();
	}

	@Override
	public List<StoreCotegory> getAllCotegoryList() {
		return storeCotegoryDao.selectAll();
	}

	@Override
	public int getAllShopSize() {
		return shopInfoDao.getAllShopSize();
	}
}
