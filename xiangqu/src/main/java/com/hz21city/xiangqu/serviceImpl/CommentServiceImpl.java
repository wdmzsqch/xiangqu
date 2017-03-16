package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.ICommentDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.Comment;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.ICommentService;

@Service("commentService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CommentServiceImpl implements ICommentService {

	@Resource
	private ICommentDao commentDao;
	@Resource
	private IUserInfoDao userInfoDao;

	@Override
	public List<Comment> getCommentList(long relativeId, int relativeType) {
		List<Comment> commentlist = commentDao.selectCommentList(relativeId, relativeType);
		for (Comment comment : commentlist) {
			UserInfo user = userInfoDao.selectByPrimaryKey(comment.getUserId());
			comment.setUser(user);
		}
		return commentlist;
	}

	@Override
	public void addComment(Comment comment) {
		commentDao.insertSelective(comment);
	}

	@Override
	public int getCommentListCount(long relativeId, int relativeType) {
		return commentDao.getCommentListCount(relativeId, relativeType);
	}

	@Override
	public List<Comment> getCommentListByuserId(Long userId) {
		List<Comment> list = commentDao.getCommentListByuserId(userId);
		return list;
	}

	@Override
	public void updataComment(Comment comment) {
		commentDao.updateByPrimaryKeySelective(comment);
	}

	@Override
	public List<Comment> getFiveComments(long relativeId, int relativeType) {
		List<Comment> commentlist = commentDao.getFiveCommentList(relativeId, relativeType);
		for (Comment comment : commentlist) {
			UserInfo user = userInfoDao.selectByPrimaryKey(comment.getUserId());
			comment.setUser(user);
		}
		return commentlist;
	}
}
