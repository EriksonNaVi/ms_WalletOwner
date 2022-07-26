package com.bootcamp.yankiwallet.events;

import java.util.Date;

import lombok.Data;

@Data
public abstract class Event<T> {

	private String id;
	private Date date;
	private EventType type;
	private T data;

}
