package com.hz21city.xiangqu.serviceImpl.wticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.IWOrderInfoDao;
import com.hz21city.xiangqu.dao.IWTicketInfoDao;
import com.hz21city.xiangqu.dao.IWUserInfoDao;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.WOrderInfo;
import com.hz21city.xiangqu.pojo.WTicketInfo;
import com.hz21city.xiangqu.pojo.WUserInfo;
import com.hz21city.xiangqu.service.wticket.IWticketService;

@Service("wticketService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class WticketServiceImpl implements IWticketService{

	@Resource
	private IWUserInfoDao wUserInfoDao;
	
	@Resource
	private IAreaDao areaDao;
	
	@Resource
	private IWTicketInfoDao wTicketInfoDao;
	
	@Resource
	private IWOrderInfoDao wOrderInfoDao;

	@Override
	public List<WUserInfo> getWUserListByPage(String keywords, Integer page, Integer searchType) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		List<WUserInfo> list = wUserInfoDao.getWUserListByPage(keywords, page, searchType);
		if(list != null && list.size() > 0){
			for (WUserInfo wUserInfo : list) {
				if(wUserInfo.getProvice() != null && wUserInfo.getProvice() != 0){
					Area area = areaDao.selectByPrimaryKey(wUserInfo.getProvice());
					if(area != null){
						wUserInfo.setProname(area.getName());
					}
				}
				if(wUserInfo.getCity() != null && wUserInfo.getCity() != 0){
					Area area = areaDao.selectByPrimaryKey(wUserInfo.getCity());
					if(area != null){
						wUserInfo.setCityname(area.getName());
					}
				}
				if(wUserInfo.getArea() != null && wUserInfo.getArea() != 0){
					Area area = areaDao.selectByPrimaryKey(wUserInfo.getArea());
					if(area != null){
						wUserInfo.setAreaname(area.getName());
					}
				}
				wUserInfo.setWnucount(wUserInfo.getWtcount()-wUserInfo.getWucount());
			}
		}
		return list;
	}

	@Override
	public int getWUserListSize(String keywords, Integer searchType) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		int total = wUserInfoDao.getWUserListSize(keywords, searchType);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public int getAllWUserCount() {
		int total = wUserInfoDao.getAllWUserCount();
		return total;
	}

	@Override
	public List<Area> getAreaListByParentid(Long parentId) {
		List<Area> list = areaDao.getAreaListByParentid(parentId);
		return list;
	}

	@Override
	public WUserInfo getWUserInfo(Long userId) {
		WUserInfo info = wUserInfoDao.selectByPrimaryKey(userId);
		return info;
	}

	@Override
	public void insertWUser(WUserInfo record) {
		wUserInfoDao.insertSelective(record);
	}

	@Override
	public void updateWUser(WUserInfo record) {
		wUserInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<WTicketInfo> getWTicketListByPage(Integer page, Integer state, String ticketNum) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(ticketNum)) {
			ticketNum = "%" + ticketNum + "%";
		}
		List<WTicketInfo> list = wTicketInfoDao.getWTicketListByPage(page, state, ticketNum);
		return list;
	}

	@Override
	public int getWTicketListSize(Integer state, String ticketNum) {
		if (!CommonUtils.isEmptyString(ticketNum)) {
			ticketNum = "%" + ticketNum + "%";
		}
		int total = wTicketInfoDao.getWTicketListSize(state, ticketNum);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public int getAllWTicketCount() {
		int total = wTicketInfoDao.getAllWTicketCount();
		return total;
	}

	@Override
	public int getAllUseWTicketCount() {
		int total = wTicketInfoDao.getAllUseWTicketCount();
		return total;
	}

	@Override
	public int getAllSellWTicketCount() {
		int total = wTicketInfoDao.getAllSellWTicketCount();
		return total;
	}

	@Override
	public List<WOrderInfo> getWOrderListByPage(String keywords, Integer page, Integer searchType, Date startDate, Date endDate) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		String startStr = null;
		if(startDate != null){
			startStr = CommonUtils.time2Str(startDate, "yyyy-MM-dd");
			startStr = startStr + " 00:00:00";
		}
		String endStr = null;
		if(endDate != null){
			endStr = CommonUtils.time2Str(endDate, "yyyy-MM-dd");
			endStr = endStr + " 23:59:59";
		}
		List<WOrderInfo> list = wOrderInfoDao.getWOrderListByPage(keywords, page, searchType, startStr, endStr);
		if(list != null && list.size() > 0){
			for (WOrderInfo wOrderInfo : list) {
				if(wOrderInfo.getWuserId() != null && wOrderInfo.getWuserId() != 0){
					WUserInfo user = wUserInfoDao.selectByPrimaryKey(wOrderInfo.getWuserId());
					if(user != null){
						if(user.getProvice() != null && user.getProvice() != 0){
							Area area = areaDao.selectByPrimaryKey(user.getProvice());
							if(area != null){
								user.setProname(area.getName());
							}
						}
						if(user.getCity() != null && user.getCity() != 0){
							Area area = areaDao.selectByPrimaryKey(user.getCity());
							if(area != null){
								user.setCityname(area.getName());
							}
						}
						if(user.getArea() != null && user.getArea() != 0){
							Area area = areaDao.selectByPrimaryKey(user.getArea());
							if(area != null){
								user.setAreaname(area.getName());
							}
						}
						wOrderInfo.setUser(user);
					}
				}
			}
		}
		return list;
	}

	@Override
	public int getWOrderListSize(String keywords, Integer searchType, Date startDate, Date endDate) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		String startStr = null;
		if(startDate != null){
			startStr = CommonUtils.time2Str(startDate, "yyyy-MM-dd");
			startStr = startStr + " 00:00:00";
		}
		String endStr = null;
		if(endDate != null){
			endStr = CommonUtils.time2Str(endDate, "yyyy-MM-dd");
			endStr = endStr + " 23:59:59";
		}
		int total = wOrderInfoDao.getWOrderListSize(keywords, searchType, startStr, endStr);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public int getAllWOrderCount() {
		int total = wOrderInfoDao.getAllWOrderCount();
		return total;
	}

	@Override
	public List<WTicketInfo> getMyTicketListByPage(Integer page, Long userId) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<WTicketInfo> list = wTicketInfoDao.getMyTicketListByPage(page,userId);
		return list;
	}

	@Override
	public int getMyTicketListSize(Long userId) {
		int total = wTicketInfoDao.getMyTicketListSize(userId);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public int getAllMyTicketCount(Long userId) {
		int total = wTicketInfoDao.getMyTicketListSize(userId);
		return total;
	}

	@Override
	public int getAllMyUseTicketCount(Long userId) {
		int total = wTicketInfoDao.getAllMyUseTicketCount(userId);
		return total;
	}

	@Override
	public int getAllMyNouseWTicketCount(Long userId) {
		int total = wTicketInfoDao.getAllMyNouseWTicketCount(userId);
		return total;
	}

	@Override
	public WTicketInfo getWTicketInfo(Long id) {
		WTicketInfo info = wTicketInfoDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public void updateWTicketInfo(WTicketInfo record) {
		wTicketInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<String> importFile(MultipartFile csvfile) {
		 	List<String> dataList=new ArrayList<String>();
		 	try {
			if (!csvfile.isEmpty()) {
				// 取得当前上传文件的文件名称
				String myFileName = csvfile.getOriginalFilename();
				String savePath = CommonUtils.time2Str(new Date(), "yyyyMMdd") + File.separator;
				String path = CommonUtils.getLocationPath() + savePath;
				// 定义上传路径
				CommonUtils.checkPath(path);
				File localOriginFile = new File(path + myFileName);
				csvfile.transferTo(localOriginFile);
				  BufferedReader br=null;
			        try { 
			            br = new BufferedReader(new FileReader(localOriginFile));
			            String line = ""; 
			            while ((line = br.readLine()) != null) { 
			                dataList.add(line);
			            }
			        }catch (Exception e) {
			        }finally{
			            if(br!=null){
			                try {
			                    br.close();
			                    br=null;
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
			            }
			        }
			}
		 }catch (Exception e) {
				return dataList;
		}
	    return dataList;
	}

	@Override
	public WTicketInfo getWTicketByTickNum(String ticketNum) {
		WTicketInfo info = wTicketInfoDao.getWTicketByTickNum(ticketNum);
		return info;
	}

	@Override
	public void insertWTicketInfo(WTicketInfo record) {
		wTicketInfoDao.insertSelective(record);
	}

	@Override
	public void insertWOrderInfo(WOrderInfo record) {
		wOrderInfoDao.insertSelective(record);
	}

	@Override
	public int getSumWOrderCount(String keywords, Integer searchType, Date startDate, Date endDate) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		String startStr = null;
		if(startDate != null){
			startStr = CommonUtils.time2Str(startDate, "yyyy-MM-dd");
			startStr = startStr + " 00:00:00";
		}
		String endStr = null;
		if(endDate != null){
			endStr = CommonUtils.time2Str(endDate, "yyyy-MM-dd");
			endStr = endStr + " 23:59:59";
		}
		int total = wOrderInfoDao.getSumWOrderCount(keywords, searchType, startStr, endStr);
		return total;
	}

	@Override
	public WUserInfo getWUserInfoByPhone(String phone) {
		WUserInfo info = wUserInfoDao.getWUserInfoByPhone(phone);
		return info;
	}

	@Override
	public List<WTicketInfo> getNoUseTicketList() {
		List<WTicketInfo> list = wTicketInfoDao.getNoUseTicketList();
		return list;
	}

}
