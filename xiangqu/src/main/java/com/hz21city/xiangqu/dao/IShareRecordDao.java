package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.ShareRecord;

public interface IShareRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ShareRecord record);

    int insertSelective(ShareRecord record);

    ShareRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShareRecord record);

    int updateByPrimaryKey(ShareRecord record);
    
    int userShare(@Param("user_id") Long user_id,@Param("type") Integer type,@Param("relative_id") Long relative_id);

	List<ShareRecord> getShareRecordByUser(@Param("user_id") Long user_id);

	void updateShareCombine(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);
}