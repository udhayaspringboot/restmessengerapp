package com.messengerapprestapi.dao;

import java.util.List;

import com.messengerapprestapi.model.Comment;

public interface CommentDao {
	
	List<Comment> getComms(int messId);
	
	int saveComms(Comment comment);
	
	int updateComms(Comment comment);
	
	int deleComms(int messId,int commId);

}
