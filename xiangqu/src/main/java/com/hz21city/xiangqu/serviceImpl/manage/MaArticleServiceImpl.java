package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IArticleInfoDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.pojo.ArticleInfo;
import com.hz21city.xiangqu.service.manage.IMaArticleService;

@Service("maArticleService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaArticleServiceImpl implements IMaArticleService{

	@Resource
	private IArticleInfoDao articleInfoDao;
	
	@Resource
	private IMissionInfoDao missionInfoDao;
	
	@Override
	public List<ArticleInfo> getArticleListByPage(int page, String key) {
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		List<ArticleInfo> list = articleInfoDao.getArticleListByPage(keywords,(page - 1) * 15);
		return list;
	}

	@Override
	public int getArticleListSize(String key) {
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		int total = articleInfoDao.getArticleListSize(keywords);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public ArticleInfo selectByPrimaryKey(Long id) {
		return articleInfoDao.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void addArticleInfo(ArticleInfo articleInfo) {
		articleInfoDao.insertSelective(articleInfo);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void updateArticleInfo(ArticleInfo articleInfo) {
		articleInfoDao.updateByPrimaryKeySelective(articleInfo);
	}

	@Override
	public int getMissionUrlArticleCount(String url) {
		String keywords = "%" + url + "%";
		return missionInfoDao.getMissionUrlArticleCount(keywords);
	}

}
