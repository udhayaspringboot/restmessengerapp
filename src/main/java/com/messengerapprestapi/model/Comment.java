package com.messengerapprestapi.model;

import java.util.Date;

public class Comment {
	

	private int commentId;
	private String comMessage;
	private Date comCreated;
	private String comAuthor;
	private int comMesFk;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComMessage() {
		return comMessage;
	}
	public void setComMessage(String comMessage) {
		this.comMessage = comMessage;
	}
	public Date getComCreated() {
		return comCreated;
	}
	public void setComCreated(Date comCreated) {
		this.comCreated = comCreated;
	}
	public String getComAuthor() {
		return comAuthor;
	}
	public void setComAuthor(String comAuthor) {
		this.comAuthor = comAuthor;
	}
	
	
	public int getComMesFk() {
		return comMesFk;
	}
	public void setComMesFk(int comMesFk) {
		this.comMesFk = comMesFk;
	}
	
	
	
	public Comment(int commentId, String comMessage, Date comCreated, String comAuthor, int comMesFk) {
		super();
		this.commentId = commentId;
		this.comMessage = comMessage;
		this.comCreated = comCreated;
		this.comAuthor = comAuthor;
		this.comMesFk = comMesFk;
	}
	public Comment(int commentId, String comMessage, Date comCreated, String comAuthor) {
		super();
		this.commentId = commentId;
		this.comMessage = comMessage;
		this.comCreated = comCreated;
		this.comAuthor = comAuthor;
	}
	public Comment() {
		super();
	}
	

}
