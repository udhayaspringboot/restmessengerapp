package com.messengerapprestapi.model;

import java.util.Date;
import java.util.List;

import javax.jws.Oneway;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Message {
	
	private int messageId;
	private String message;
	private Date created;
	private String author;
	
	private List<Comment> comment;
	
	private List<Link> links;
	
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	

	@XmlTransient
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	
	public Message(int messageId, String message, Date created, String author) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.created = created;
		this.author = author;
	}
	public Message() {
		super();
	}

}
