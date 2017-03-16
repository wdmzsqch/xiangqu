package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.ArticleInfo;

public interface IMaArticleService {

	List<ArticleInfo> getArticleListByPage(int page, String keywords);

	int getArticleListSize(String keywords);

	ArticleInfo selectByPrimaryKey(Long id);

	void addArticleInfo(ArticleInfo articleInfo);

	void updateArticleInfo(ArticleInfo articleInfo);

	int getMissionUrlArticleCount(String url);

}
