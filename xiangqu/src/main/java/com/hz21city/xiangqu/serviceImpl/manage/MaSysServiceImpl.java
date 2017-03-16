package com.hz21city.xiangqu.serviceImpl.manage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IAdPointDao;
import com.hz21city.xiangqu.dao.IAdminRoleDao;
import com.hz21city.xiangqu.dao.ICardInfoDao;
import com.hz21city.xiangqu.dao.ICardRecordInfoDao;
import com.hz21city.xiangqu.dao.ICgrecordInfoDao;
import com.hz21city.xiangqu.dao.IChannelInfoDao;
import com.hz21city.xiangqu.dao.IEventInfoDao;
import com.hz21city.xiangqu.dao.IHomeActivityDao;
import com.hz21city.xiangqu.dao.IMarkAdPointDao;
import com.hz21city.xiangqu.dao.ISubjectInfoDao;
import com.hz21city.xiangqu.dao.ISystemMessageDao;
import com.hz21city.xiangqu.dao.IMissionCatogryDao;
import com.hz21city.xiangqu.pojo.AdPoint;
import com.hz21city.xiangqu.pojo.AdminRole;
import com.hz21city.xiangqu.pojo.CardInfo;
import com.hz21city.xiangqu.pojo.CardRecordInfo;
import com.hz21city.xiangqu.pojo.CgrecordInfo;
import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.MarkAdPoint;
import com.hz21city.xiangqu.pojo.MissionCatogry;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.service.manage.IMaSysService;

@Service("maSysService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaSysServiceImpl implements IMaSysService {
	@Resource
	private IAdminRoleDao adminRoleDao;

	@Resource
	private IMissionCatogryDao missionCatogryDao;

	@Resource
	private IHomeActivityDao homeactivityDao;

	@Resource
	private IEventInfoDao eventInfoDao;

	@Resource
	private ISubjectInfoDao subjectInfoDao;

	@Resource
	private ISystemMessageDao systemMessageDao;

	@Resource
	private IAdPointDao adPointDao;

	@Resource
	private IMarkAdPointDao markAdPointDao;
	
	@Resource
	private ICardInfoDao cardInfoDao;
	
	@Resource
	private ICardRecordInfoDao cardRecordInfoDao;
	
	@Resource
	private IChannelInfoDao channelInfoDao;
	
	@Resource
	private ICgrecordInfoDao cgrecordInfoDao;

	@Override
	public List<AdminRole> getAdminRoleList() {
		return adminRoleDao.getAllAdminRole();
	}

	@Override
	public AdminRole selectByPrimaryKey(Long id) {
		return adminRoleDao.selectByPrimaryKey(id);
	}

	@Override
	public void insert(AdminRole role) {
		adminRoleDao.insertSelective(role);
	}

	@Override
	public void updateByPrimaryKeySelective(AdminRole role) {
		adminRoleDao.updateByPrimaryKeySelective(role);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		adminRoleDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<MissionCatogry> getMisstionCatogryList(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		return missionCatogryDao.getMissionCatogryList(page);
	}

	@Override
	public int getMisstionCatogryListSize() {
		int total = missionCatogryDao.getMissionCatogryListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public MissionCatogry selectCatogryByPrimaryKey(Long id) {
		return missionCatogryDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertCatogry(MissionCatogry catogry) {
		missionCatogryDao.insertSelective(catogry);
	}

	@Override
	public void updateCatogryByPrimaryKeySelective(MissionCatogry catogry) {
		missionCatogryDao.updateByPrimaryKey(catogry);
	}

	@Override
	public HomeActivity getHomeActivityByKey(String valuekey) {
		HomeActivity activity = homeactivityDao.getActivityByValueKey(valuekey);
		if (activity != null) {
			if (activity.getType() != null) {
				if (activity.getType() == 1) {
					EventInfo event = eventInfoDao.selectByPrimaryKey(activity.getRelatived_id());
					if (event != null) {
						activity.setActivityname(event.getName());
					}
				} else if (activity.getType() == 2) {
					SubjectInfo subjectinfo = subjectInfoDao.selectByPrimaryKey(activity.getRelatived_id());
					if (subjectinfo != null) {
						activity.setActivityname(subjectinfo.getName());
					}
				}
			}
		}
		return activity;
	}

	@Override
	public void insertHomeActivity(HomeActivity homeactivity) {
		homeactivityDao.insertSelective(homeactivity);
	}

	@Override
	public void updateHomeActivity(HomeActivity homeactivity) {
		homeactivityDao.updateByPrimaryKeySelective(homeactivity);
	}

	@Override
	public void sendSysmessage(SystemMessage systemMessage) {
		systemMessageDao.insertSelective(systemMessage);
	}

	@Override
	public List<SystemMessage> getSystemMessageListByPage(int page) {
		page = (page - 1) * 10;
		List<SystemMessage> systemmessagelist = systemMessageDao.getSystemMessageListByPage(page);
		return systemmessagelist;
	}

	@Override
	public int getSystemMessageListSize() {
		int total = systemMessageDao.getSystemMessageListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public void delSystemMessage(long id) {
		systemMessageDao.deleteByPrimaryKey(id);
	}

	@Override
	public int getNewSysmessage(String startTime) {
		return systemMessageDao.getNewSysmessage(startTime);
	}

	@Override
	public List<AdPoint> getAdPointList(Integer page, String keywords, String SourceClass, String areaSearch) {
		if (page == null) {

			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 40;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		List<AdPoint> list = adPointDao.getAdPointListByPage(page, keywords, SourceClass, areaSearch);
		return list;
	}

	@Override
	public int getAdPointListSize(String keywords, String SourceClass, String areaSearch) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		int total = adPointDao.getAdPointListSize(keywords, SourceClass, areaSearch);
		return (int) Math.ceil((double) total / 40.0d);
	}

	@Override
	public AdPoint selectAdPointByPrimaryKey(Long id) {
		return adPointDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateAdPointByPrimaryKeySelective(AdPoint adpoint) {
		adPointDao.updateByPrimaryKeySelective(adpoint);
	}

	@Override
	public void insertAdPoint(AdPoint adpoint) {
		adPointDao.insertSelective(adpoint);
	}

	@Override
	public List<AdPoint> getAdPointListByLoLa(double longitude, double latitude, String keywords, String SourceClass, String areaSearch) {
		double slongitude = longitude - 0.086;
		double elongitude = longitude + 0.086;
		double slatitude = latitude - 0.054;
		double elatitude = latitude + 0.054;
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		List<AdPoint> list = adPointDao.getAdPointListByLoLa(slongitude, elongitude, slatitude, elatitude, keywords, SourceClass, areaSearch);
		return list;
	}

	@Override
	public void deleteAdPointByKey(Long id) {
		adPointDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insertMarkAdPoint(MarkAdPoint markap) {
		markAdPointDao.insertSelective(markap);
	}

	@Override
	public List<AdPoint> getAdPointListByLoLaIds(double longitude, double latitude, List<Long> pointlist) {
		double slongitude = longitude - 0.086;
		double elongitude = longitude + 0.086;
		double slatitude = latitude - 0.054;
		double elatitude = latitude + 0.054;
		List<AdPoint> list = adPointDao.getAdPointListByLoLaIds(slongitude, elongitude, slatitude, elatitude, pointlist);
		return list;
	}

	@Override
	public List<MarkAdPoint> getMarkAdPointList() {
		List<MarkAdPoint> list = markAdPointDao.getMarkAdPointList();
		if (list != null && list.size() > 0) {
			for (MarkAdPoint markAdPoint : list) {
				String pointids = markAdPoint.getPointId();
				if (!CommonUtils.isEmptyString(pointids)) {
					String[] strlist = pointids.split(",");
					if (strlist != null && strlist.length > 0) {
						String pointname = "";
						for (int i = 0; i < strlist.length; i++) {
							AdPoint point = adPointDao.selectByPrimaryKey(CommonUtils.parseLong(strlist[i], 0));
							if (point != null) {
								if (CommonUtils.isEmptyString(pointname)) {
									pointname = point.getPlotName();
								} else {
									pointname += "," + point.getPlotName();
								}
								markAdPoint.setPointname(pointname);
							}
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public MarkAdPoint selectMarkAdPointByKey(long id) {
		MarkAdPoint info = markAdPointDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public void deleteMarkAdPointByKey(long id) {
		markAdPointDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<AdPoint> getMarkAdPointListLfive(List<Long> pointlist) {
		List<AdPoint> list = adPointDao.getMarkAdPointListLfive(pointlist);
		return list;
	}

	@Override
	public List<AdPoint> getMapList(String keywords, String SourceClass, String areaSearch) {
		List<AdPoint> list = adPointDao.getMapList(keywords, SourceClass, areaSearch);
		return list;
	}

	@Override
	public AdPoint getAdPointById(Long id) {
		AdPoint info = adPointDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public List<AdPoint> getMarkPointList(List<Long> pointlist) {
		List<AdPoint> list = adPointDao.getMarkPointList(pointlist);
		return list;
	}

	@Override
	public MarkAdPoint getMarkAdPointByid(Long id) {
		MarkAdPoint info = markAdPointDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public void updateMarkAdPoint(MarkAdPoint point) {
		markAdPointDao.updateByPrimaryKeySelective(point);
	}

	@Override
	public List<AdPoint> getAdPointListByLoLa2(double longitude, double latitude, String keywords, String SourceClass, String areaSearch) {
		// double slongitude = longitude-0.025;
		// double elongitude = longitude+0.025;
		// double slatitude = latitude-0.014;
		// double elatitude = latitude+0.014;
		double slongitude = longitude - 0.010;
		double elongitude = longitude + 0.010;
		double slatitude = latitude - 0.008;
		double elatitude = latitude + 0.008;
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		List<AdPoint> list = adPointDao.getAdPointListByLoLa(slongitude, elongitude, slatitude, elatitude, keywords, SourceClass, areaSearch);
		return list;
	}

	@Override
	public List<AdPoint> getAdPointListByLoLaIds2(double longitude, double latitude, List<Long> pointlist) {
		// double slongitude = longitude-0.025;
		// double elongitude = longitude+0.025;
		// double slatitude = latitude-0.014;
		// double elatitude = latitude+0.014;
		double slongitude = longitude - 0.010;
		double elongitude = longitude + 0.010;
		double slatitude = latitude - 0.008;
		double elatitude = latitude + 0.008;
		List<AdPoint> list = adPointDao.getAdPointListByLoLaIds(slongitude, elongitude, slatitude, elatitude, pointlist);
		return list;
	}

	@Override
	public List<AdPoint> getAdPointList(Integer page, String keywords, String SourceClass, String areaSearch, int areatype) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 40;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		List<AdPoint> list = adPointDao.getAdPointListByPageByArea(page, keywords, SourceClass, areaSearch, areatype);
		return list;
	}

	@Override
	public int getAdPointListSize(String keywords, String SourceClass, String areaSearch, int areatype) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		int total = adPointDao.getAdPointListSizeByArea(keywords, SourceClass, areaSearch, areatype);
		return (int) Math.ceil((double) total / 40.0d);
	}

	@Override
	public List<AdPoint> getAdPointListByLoLa2(double longitude, double latitude, String keywords, String SourceClass, String areaSearch, int areatype) {
		double slongitude = longitude - 0.010;
		double elongitude = longitude + 0.010;
		double slatitude = latitude - 0.008;
		double elatitude = latitude + 0.008;
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		if (!CommonUtils.isEmptyString(SourceClass)) {
			SourceClass = "%" + SourceClass + "%";
		}
		if (!CommonUtils.isEmptyString(areaSearch)) {
			areaSearch = "%" + areaSearch + "%";
		}
		List<AdPoint> list = adPointDao.getAdPointListByLoLaByArea(slongitude, elongitude, slatitude, elatitude, keywords, SourceClass, areaSearch, areatype);
		return list;
	}

	@Override
	public List<AdPoint> getAdPointListByLoLaIds2(double longitude, double latitude, List<Long> pointlist, int areatype) {
		double slongitude = longitude - 0.010;
		double elongitude = longitude + 0.010;
		double slatitude = latitude - 0.008;
		double elatitude = latitude + 0.008;
		List<AdPoint> list = adPointDao.getAdPointListByLoLaIdsByArea(slongitude, elongitude, slatitude, elatitude, pointlist, areatype);
		return list;
	}

	@Override
	public List<AdPoint> getMapList(String keywords, String SourceClass, String areaSearch, int areatype) {
		List<AdPoint> list = adPointDao.getMapListByArea(keywords, SourceClass, areaSearch,areatype);
		return list;
	}

	@Override
	public List<MarkAdPoint> getMarkAdPointList(int areatype) {
		List<MarkAdPoint> list = markAdPointDao.getMarkAdPointListByArea(areatype);
		if (list != null && list.size() > 0) {
			for (MarkAdPoint markAdPoint : list) {
				String pointids = markAdPoint.getPointId();
				if (!CommonUtils.isEmptyString(pointids)) {
					String[] strlist = pointids.split(",");
					if (strlist != null && strlist.length > 0) {
						String pointname = "";
						for (int i = 0; i < strlist.length; i++) {
							AdPoint point = adPointDao.selectByPrimaryKey(CommonUtils.parseLong(strlist[i], 0));
							if (point != null) {
								if (CommonUtils.isEmptyString(pointname)) {
									pointname = point.getPlotName();
								} else {
									pointname += "," + point.getPlotName();
								}
								markAdPoint.setPointname(pointname);
							}
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<CardInfo> getCardList(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<CardInfo> list = cardInfoDao.getCardListByPage(page);
		return list;
	}

	@Override
	public int getCardListSize() {
		int total = cardInfoDao.getCardListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public CardInfo getCardByNum(String cardNum) {
		CardInfo info = cardInfoDao.getCardByNum(cardNum);
		return info;
	}

	@Override
	public void insertCardInfo(CardInfo info) {
		cardInfoDao.insertSelective(info);
	}

	@Override
	public void importExcel(String filepath) {
		String fileType = filepath.substring(filepath.lastIndexOf(".")).toLowerCase();
		try {
			InputStream stream = new FileInputStream(filepath);
			Workbook wb = null;
			if (fileType.equals(".xls")) {
				wb = new HSSFWorkbook(stream);
			} else if (fileType.equals(".xlsx")) {
				wb = new XSSFWorkbook(stream);
			} else {
			}
			Sheet sheet = wb.getSheetAt(0);
			// 循环行Row
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				// 标题行
				Row titlerow = sheet.getRow(0);
				if (row == null) {
					continue;
				}
				String cardNum = "";
				String cardPwd = "";
				// 循环列Cell
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					Cell titlecell = titlerow.getCell(j);
					if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if (titlecell.getStringCellValue().equals("卡号")) {
//							info.setCardNum(cell.getStringCellValue());
							cardNum = cell.getStringCellValue();
						} else if (titlecell.getStringCellValue().equals("密码")) {
//							info.setCardPwd(cell.getStringCellValue());
							cardPwd = cell.getStringCellValue();
						} 
					}
				}
				CardInfo card = cardInfoDao.getCardByNum(cardNum);
				if(card == null){
					if(!CommonUtils.isEmptyString(cardNum) && !CommonUtils.isEmptyString(cardPwd)){
						CardInfo info = new CardInfo();
						info.setCardNum(cardNum);
						info.setCardPwd(cardPwd);
						info.setState(0);
						cardInfoDao.insertSelective(info);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CardRecordInfo> getCardRecordList(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<CardRecordInfo> list = cardRecordInfoDao.getCardRecordListByPage(page);
		if(list != null && list.size() > 0){
			for (CardRecordInfo cardRecordInfo : list) {
				if(cardRecordInfo.getCardId() != null && cardRecordInfo.getCardId() != 0){
					CardInfo card = cardInfoDao.selectByPrimaryKey(cardRecordInfo.getCardId());
					if(card != null){
						cardRecordInfo.setCard_num(card.getCardNum());
					}
				}
			}
		}
		return list;
	}

	@Override
	public int getCardRecordListSize() {
		int total = cardRecordInfoDao.getCardRecordListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public List<ChannelInfo> getChannelList(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<ChannelInfo> list = channelInfoDao.getChannelListByPage(page);
		return list;
	}

	@Override
	public int getChannelListSize() {
		int total = channelInfoDao.getChannelListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public Integer getChannelCountByCode(String randNum) {
		return channelInfoDao.getChannelCountCode(randNum);
	}

	@Override
	public void insertChannelInfo(ChannelInfo channel) {
		channelInfoDao.insertSelective(channel);
	}

	@Override
	public ChannelInfo getChannelInfoById(Long id) {
		ChannelInfo info = channelInfoDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public void updateChannelInfo(ChannelInfo info) {
		channelInfoDao.updateByPrimaryKeySelective(info);
	}

	@Override
	public ChannelInfo getChannelInfoByCode(String channelCode) {
		ChannelInfo info = channelInfoDao.getChannelInfoByCode(channelCode);
		return info;
	}

	@Override
	public void insertCgrecordInfo(CgrecordInfo record) {
		cgrecordInfoDao.insert(record);
	}
}
