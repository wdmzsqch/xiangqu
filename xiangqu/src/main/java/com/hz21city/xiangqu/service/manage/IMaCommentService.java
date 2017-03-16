package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.Comment;

public interface IMaCommentService {

	List<Comment> getCommentList(int page, String keywords);

	int getCommentListSize(String keywords);

	void delComment(Long id);
}
