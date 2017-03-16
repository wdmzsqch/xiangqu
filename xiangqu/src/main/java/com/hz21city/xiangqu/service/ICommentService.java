package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.Comment;

public interface ICommentService {

	public List<Comment> getCommentList(long relativeId, int relativeType);

	public void addComment(Comment comment);

	public int getCommentListCount(long relativeId, int relativeType);

	public List<Comment> getCommentListByuserId(Long combineduserid);

	public void updataComment(Comment comment);

	public List<Comment> getFiveComments(long relativeId, int relativeType);
}
