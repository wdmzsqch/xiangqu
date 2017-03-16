package com.hz21city.xiangqu.service.wticket;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.WOrderInfo;
import com.hz21city.xiangqu.pojo.WTicketInfo;
import com.hz21city.xiangqu.pojo.WUserInfo;

public interface IWticketService {

	List<WUserInfo> getWUserListByPage(String keywords, Integer page, Integer searchType);

	int getWUserListSize(String keywords, Integer searchType);

	int getAllWUserCount();

	List<Area> getAreaListByParentid(Long parentId);

	WUserInfo getWUserInfo(Long userId);

	void insertWUser(WUserInfo info);

	void updateWUser(WUserInfo info);

	List<WTicketInfo> getWTicketListByPage(Integer page, Integer state, String ticketNum);

	int getWTicketListSize(Integer state, String ticketNum);

	int getAllWTicketCount();

	int getAllUseWTicketCount();

	int getAllSellWTicketCount();

	List<WOrderInfo> getWOrderListByPage(String keywords, Integer page, Integer searchType, Date startDate, Date endDate);

	int getWOrderListSize(String keywords, Integer searchType, Date startDate, Date endDate);

	int getAllWOrderCount();

	List<WTicketInfo> getMyTicketListByPage(Integer page, Long userId);

	int getMyTicketListSize(Long userId);

	int getAllMyTicketCount(Long userId);

	int getAllMyUseTicketCount(Long userId);

	int getAllMyNouseWTicketCount(Long userId);

	WTicketInfo getWTicketInfo(Long id);

	void updateWTicketInfo(WTicketInfo info);

	List<String> importFile(MultipartFile csvfile);

	WTicketInfo getWTicketByTickNum(String ticketNum);

	void insertWTicketInfo(WTicketInfo ticket);

	void insertWOrderInfo(WOrderInfo record);

	int getSumWOrderCount(String keywords, Integer searchType, Date startDate, Date endDate);

	WUserInfo getWUserInfoByPhone(String phone);

	List<WTicketInfo> getNoUseTicketList();



}
