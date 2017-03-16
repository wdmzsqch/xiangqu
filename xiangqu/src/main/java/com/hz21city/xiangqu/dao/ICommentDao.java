package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.Comment;

public interface ICommentDao {

	int deleteByPrimaryKey(Long id);

	int insert(Comment record);

	int insertSelective(Comment record);

	Comment selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Comment record);

	int updateByPrimaryKeyWithBLOBs(Comment record);

	int updateByPrimaryKey(Comment record);

	List<Comment> selectCommentList(@Param("relativeId") long relativeId, @Param("relativeType") int relativeType);

	List<Comment> getCommentPageList(@Param("page") Integer page, @Param("keywords") String keywords);

	int getCommentListCount(@Param("relativeId") Long relativeId, @Param("relativeType") Integer relativeType);

	List<Comment> getCommentListByuserId(@Param("userId") Long userId);

	void updateCombineComment(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);

	List<Comment> getFiveCommentList(@Param("relativeId") long relativeId, @Param("relativeType") int relativeType);
}