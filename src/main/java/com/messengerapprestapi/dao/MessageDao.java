package com.messengerapprestapi.dao;

import java.util.List;

import com.messengerapprestapi.model.Message;

public interface MessageDao {
	
	
	int saveMessage(Message message);
	List<Message> getAllMessages();
	Message getMessage(int messId);
	
	int updateMessage(Message message);
	int deleteMessage(int messId);
	

}
