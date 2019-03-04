package com.zt.yundan.bean;

import java.io.Serializable;


public class Unloading implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer unloadingId;
	private String unloadingName;
	private String unloadingShortname;
	private String unloadingAddress;
	private String unloadingPerson;
	private String unloadingPhone;

	public Integer getUnloadingId() {
		return unloadingId;
	}

	public void setUnloadingId(Integer unloadingId) {
		this.unloadingId = unloadingId;
	}

	public String getUnloadingName() {
		return unloadingName;
	}

	public void setUnloadingName(String unloadingName) {
		this.unloadingName = unloadingName;
	}

	public String getUnloadingShortname() {
		return unloadingShortname;
	}

	public void setUnloadingShortname(String unloadingShortname) {
		this.unloadingShortname = unloadingShortname;
	}

	public String getUnloadingAddress() {
		return unloadingAddress;
	}

	public void setUnloadingAddress(String unloadingAddress) {
		this.unloadingAddress = unloadingAddress;
	}

	public String getUnloadingPerson() {
		return unloadingPerson;
	}

	public void setUnloadingPerson(String unloadingPerson) {
		this.unloadingPerson = unloadingPerson;
	}

	public String getUnloadingPhone() {
		return unloadingPhone;
	}

	public void setUnloadingPhone(String unloadingPhone) {
		this.unloadingPhone = unloadingPhone;
	}
}