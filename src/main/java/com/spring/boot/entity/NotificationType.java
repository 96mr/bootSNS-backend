package com.spring.boot.entity;

public enum NotificationType {
	LIKE("like"),
	REPLY("reply"),
	FOLLOW("follow");
	
	private final String value;
	
	NotificationType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
