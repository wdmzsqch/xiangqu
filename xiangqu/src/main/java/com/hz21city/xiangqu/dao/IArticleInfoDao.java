package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.ArticleInfo;

public interface IArticleInfoDao {

	int deleteByPrimaryKey(Long id);

	int insert(ArticleInfo record);

	int insertSelective(ArticleInfo record);

	ArticleInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(ArticleInfo record);

	int updateByPrimaryKeyWithBLOBs(ArticleInfo record);

	int updateByPrimaryKey(ArticleInfo record);

	// List<ArticleInfo> selectAll();
	List<ArticleInfo> getArticleListByPage(@Param("keywords") String keywords, @Param("page") Integer page);

	int getArticleListSize(@Param("keywords") String keywords);
}