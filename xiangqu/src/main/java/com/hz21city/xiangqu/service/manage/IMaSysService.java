package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.AdPoint;
import com.hz21city.xiangqu.pojo.AdminRole;
import com.hz21city.xiangqu.pojo.CardInfo;
import com.hz21city.xiangqu.pojo.CardRecordInfo;
import com.hz21city.xiangqu.pojo.CgrecordInfo;
import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.MarkAdPoint;
import com.hz21city.xiangqu.pojo.MissionCatogry;
import com.hz21city.xiangqu.pojo.SystemMessage;

public interface IMaSysService {
	public abstract List<AdminRole> getAdminRoleList();

	public abstract AdminRole selectByPrimaryKey(Long id);

	public abstract void insert(AdminRole role);

	public abstract void updateByPrimaryKeySelective(AdminRole role);

	public abstract void deleteByPrimaryKey(Long id);

	public abstract List<MissionCatogry> getMisstionCatogryList(Integer page);

	public abstract int getMisstionCatogryListSize();

	public abstract MissionCatogry selectCatogryByPrimaryKey(Long id);

	public abstract void insertCatogry(MissionCatogry catogry);

	public abstract void updateCatogryByPrimaryKeySelective(MissionCatogry catogry);

	public abstract HomeActivity getHomeActivityByKey(String valuekey);

	public abstract void insertHomeActivity(HomeActivity homeactivity);

	public abstract void updateHomeActivity(HomeActivity homeactivity);
	
	public abstract void sendSysmessage(SystemMessage systemMessage);
	
	public abstract List<SystemMessage> getSystemMessageListByPage(int page);
	
	public abstract int getSystemMessageListSize();
	
	public abstract void delSystemMessage(long id);

	public abstract int getNewSysmessage(String startTime);

	public abstract List<AdPoint> getAdPointList(Integer page, String keywords, String SourceClass, String areaSearch);

	public abstract int getAdPointListSize(String keywords, String SourceClass, String areaSearch);

	public abstract AdPoint selectAdPointByPrimaryKey(Long id);

	public abstract void updateAdPointByPrimaryKeySelective(AdPoint adpoint);

	public abstract void insertAdPoint(AdPoint adpoint);

	public abstract List<AdPoint> getAdPointListByLoLa(double longitude, double latitude, String keywords, String SourceClass, String areaSearch);

	public abstract void deleteAdPointByKey(Long id);

	public abstract void insertMarkAdPoint(MarkAdPoint markap);

	public abstract List<AdPoint> getAdPointListByLoLaIds(double longitude, double latitude, List<Long> pointlist);

	public abstract List<MarkAdPoint> getMarkAdPointList();

	public abstract MarkAdPoint selectMarkAdPointByKey(long id);

	public abstract void deleteMarkAdPointByKey(long id);

	public abstract List<AdPoint> getMarkAdPointListLfive(List<Long> pointlist);

	public abstract List<AdPoint> getMapList(String keywords, String SourceClass, String areaSearch);

	public abstract AdPoint getAdPointById(Long id);

	public abstract List<AdPoint> getMarkPointList(List<Long> pointlist);

	public abstract MarkAdPoint getMarkAdPointByid(Long id);

	public abstract void updateMarkAdPoint(MarkAdPoint point);

	public abstract List<AdPoint> getAdPointListByLoLa2(double longitude, double latitude, String keywords, String sourceClass, String areaSearch);

	public abstract List<AdPoint> getAdPointListByLoLaIds2(double longitude, double latitude, List<Long> pointlist);

	public abstract List<AdPoint> getAdPointList(Integer page, String keywords, String SourceClass, String areaSearch, int areatype);

	public abstract int getAdPointListSize(String keywords, String SourceClass, String areaSearch, int areatype);

	public abstract List<AdPoint> getAdPointListByLoLa2(double longitude, double latitude, String keywords, String sourceClass, String areaSearch, int areatype);

	public abstract List<AdPoint> getAdPointListByLoLaIds2(double longitude, double latitude, List<Long> pointlist, int areatype);

	public abstract List<AdPoint> getMapList(String keywords, String SourceClass, String areaSearch, int areatype);

	public abstract List<MarkAdPoint> getMarkAdPointList(int areatype);

	public abstract List<CardInfo> getCardList(Integer page);

	public abstract int getCardListSize();

	public abstract CardInfo getCardByNum(String cardNum);

	public abstract void insertCardInfo(CardInfo info);

	public abstract void importExcel(String filepath);

	public abstract List<CardRecordInfo> getCardRecordList(Integer page);

	public abstract int getCardRecordListSize();

	public abstract List<ChannelInfo> getChannelList(Integer page);

	public abstract int getChannelListSize();

	public abstract Integer getChannelCountByCode(String randNum);

	public abstract void insertChannelInfo(ChannelInfo channel);

	public abstract ChannelInfo getChannelInfoById(Long id);

	public abstract void updateChannelInfo(ChannelInfo info);

	public abstract ChannelInfo getChannelInfoByCode(String channelCode);

	public abstract void insertCgrecordInfo(CgrecordInfo record);
}
