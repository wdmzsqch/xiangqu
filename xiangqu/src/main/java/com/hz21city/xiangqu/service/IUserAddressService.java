package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.UserAddress;

public interface IUserAddressService {

	public abstract UserAddress getUserDefaultAddress(long userid);
	
	public abstract UserAddress getAddress(long addressid);
	
	public abstract List<UserAddress> getUserAddress(long userid);
	
	public abstract List<Area> getAreaList(long parentid);
	
	public abstract void addUserAddress(UserAddress useraddress);
	
	public abstract void updateUserAddress(UserAddress useraddress);
}
