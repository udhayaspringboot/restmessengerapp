package com.messengerapprestapi.service;

import java.util.List;

import com.messengerapprestapi.dao.CommentDao;
import com.messengerapprestapi.dao.CommentdaoImpl;
import com.messengerapprestapi.model.Comment;
import com.messengerapprestapi.model.Message;

public class CommentServicce {
	
	CommentDao cmDao = new CommentdaoImpl();
	
	public List<Comment> getAllComms(int messId){
		
		List<Comment> lisd = cmDao.getComms(messId);
			
		
		for (Comment comment : lisd) {
			System.out.println(comment.getComMessage());
		}
		
		
		return lisd;}
		

	public int saveCom(Comment comment)
	{
		cmDao.saveComms(comment);
		
		return 0;
	}
	
	public int updateComm(Comment comment)
	{
		cmDao.updateComms(comment);
		return 0;
	}
	
	public int deleComm(int messId,int commId)
	{
		cmDao.deleComms(messId, commId);
		return 0;
	}
	
}
