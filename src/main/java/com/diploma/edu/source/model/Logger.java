package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

import java.util.Date;

@ObjectType(id = 1)
public class Logger extends BaseEntity {

	@Attribute(id = 1,clazz = Entrance.class)
	protected Entrance entrance;

	@Attribute(id = 2,clazz = Ekey.class)
	protected Ekey eKey;

	@Attribute(id = 3, valueType = ValueType.DATE_VALUE)
	protected Date dateAndTime;

	@Attribute(id = 4, valueType = ValueType.VALUE)
	protected String message;

	public Entrance getEntrance() {
		return entrance;
	}

	public void setEntrance(Entrance entrance) {
		this.entrance = entrance;
	}

	public Ekey geteKey() {
		return eKey;
	}

	public void seteKey(Ekey eKey) {
		this.eKey = eKey;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
