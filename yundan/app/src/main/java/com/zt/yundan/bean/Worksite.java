/** 
 *@Project: yundan 
 *@Author: chenlijun
 *@Date: 2019年2月21日 
 *@Copyright: ©2016-2022 http://www.zhtkj.com Inc. All rights reserved. 
 */    
package com.zt.yundan.bean;

import java.io.Serializable;

/**
 * ClassName: Worksite 
 * @Description: TODO
 * @date 2019年2月21日
 */

public class Worksite implements Serializable {
	
	private static final long serialVersionUID = 252179300605959883L;
	
	private Integer worksiteId;
	private String worksiteName;
	private String worksiteShortname;
	private String worksiteAddress;
	private String worksitePerson;
	private String worksitePhone;

	public Integer getWorksiteId() {
		return worksiteId;
	}

	public void setWorksiteId(Integer worksiteId) {
		this.worksiteId = worksiteId;
	}

	public String getWorksiteName() {
		return worksiteName;
	}

	public void setWorksiteName(String worksiteName) {
		this.worksiteName = worksiteName;
	}

	public String getWorksiteShortname() {
		return worksiteShortname;
	}

	public void setWorksiteShortname(String worksiteShortname) {
		this.worksiteShortname = worksiteShortname;
	}

	public String getWorksiteAddress() {
		return worksiteAddress;
	}

	public void setWorksiteAddress(String worksiteAddress) {
		this.worksiteAddress = worksiteAddress;
	}

	public String getWorksitePerson() {
		return worksitePerson;
	}

	public void setWorksitePerson(String worksitePerson) {
		this.worksitePerson = worksitePerson;
	}

	public String getWorksitePhone() {
		return worksitePhone;
	}

	public void setWorksitePhone(String worksitePhone) {
		this.worksitePhone = worksitePhone;
	}
}
