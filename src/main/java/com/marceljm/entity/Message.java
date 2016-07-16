package com.marceljm.entity;

import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@Named
@XmlRootElement
public class Message {

	private String message;

	public Message() {
	}

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
