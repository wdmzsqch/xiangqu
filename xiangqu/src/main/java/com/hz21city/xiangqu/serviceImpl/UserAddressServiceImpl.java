package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.IUserAddressDao;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.service.IUserAddressService;

@Service("userAddressService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserAddressServiceImpl implements IUserAddressService{

	@Resource
	private IUserAddressDao useraddressDao;

	@Resource
	private IAreaDao areaDao;
	
	@Override
	public UserAddress getUserDefaultAddress(long userid) {
		UserAddress useraddress = useraddressDao.getUserDefaultAddress(userid);
		if(useraddress!=null){
			if(useraddress.getProvince()!=null&&useraddress.getProvince()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getProvince());
				if(area!=null){
					useraddress.setProname(area.getName());
				}
			}
			if(useraddress.getCity()!=null&&useraddress.getCity()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getCity());
				if(area!=null){
					useraddress.setCityname(area.getName());
				}
			}
			if(useraddress.getArea()!=null&&useraddress.getArea()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getArea());
				if(area!=null){
					useraddress.setAreaname(area.getName());
				}
			}
		}
		return useraddress;
	}

	@Override
	public UserAddress getAddress(long addressid) {
		UserAddress useraddress = useraddressDao.selectByPrimaryKey(addressid);
		if(useraddress!=null){
			if(useraddress.getProvince()!=null&&useraddress.getProvince()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getProvince());
				if(area!=null){
					useraddress.setProname(area.getName());
				}
			}
			if(useraddress.getCity()!=null&&useraddress.getCity()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getCity());
				if(area!=null){
					useraddress.setCityname(area.getName());
				}
			}
			if(useraddress.getArea()!=null&&useraddress.getArea()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getArea());
				if(area!=null){
					useraddress.setAreaname(area.getName());
				}
			}
		}
		return useraddress;
	}

	@Override
	public List<UserAddress> getUserAddress(long userid) {
		List<UserAddress> useraddresslist = useraddressDao.getUserAddress(userid);
		for(UserAddress useraddress:useraddresslist){
			if(useraddress.getProvince()!=null&&useraddress.getProvince()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getProvince());
				if(area!=null){
					useraddress.setProname(area.getName());
				}
			}
			if(useraddress.getCity()!=null&&useraddress.getCity()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getCity());
				if(area!=null){
					useraddress.setCityname(area.getName());
				}
			}
			if(useraddress.getArea()!=null&&useraddress.getArea()>0){
				Area area = areaDao.selectByPrimaryKey(useraddress.getArea());
				if(area!=null){
					useraddress.setAreaname(area.getName());
				}
			}
		}
		return useraddresslist;
	}

	@Override
	public List<Area> getAreaList(long parentid) {
		List<Area> arealist = areaDao.getAreaListByParentid(parentid);
		return arealist;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addUserAddress(UserAddress useraddress){
		useraddressDao.insertSelective(useraddress);
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUserAddress(UserAddress useraddress){
		useraddressDao.updateByPrimaryKeySelective(useraddress);
	}
}
