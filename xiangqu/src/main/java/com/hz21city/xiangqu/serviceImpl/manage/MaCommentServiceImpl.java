package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.ICommentDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.Comment;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.manage.IMaCommentService;

@Service("maCommentService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaCommentServiceImpl implements IMaCommentService {

	@Resource
	private ICommentDao commentDao;
	@Resource
	private IUserInfoDao userInfoDao;

	@Override
	public List<Comment> getCommentList(int page, String keywords) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		List<Comment> commentlist = commentDao.getCommentPageList((page - 1) * 15, keywords);
		for (Comment comment : commentlist) {
			UserInfo user = userInfoDao.selectByPrimaryKey(comment.getUserId());
			comment.setUser(user);
		}
		return commentlist;
	}

	@Override
	public int getCommentListSize(String keywords) {
		// String keywords = null;
		// if (!CommonUtils.isEmptyString(key)) {
		// keywords = "%" + key + "%";
		// }
		int total = commentDao.getCommentListCount(null, null);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public void delComment(Long id) {
		commentDao.deleteByPrimaryKey(id);
	}
}
