package com.zt.yundan.bean;

import java.io.Serializable;

/**
 * 
 * ClassName: User 
 * @Description: 用户实体类
 * @date 2019年2月20日
 */
public class User implements Serializable{
	//用户ID
	private String userId ;
	//用户密码
	private String password;
	//用户帐号
	private String username;
	//姓名 
	private String realName;
	//角色
	private Integer role;
	//类型
	private Integer type;
	//权限
	private Integer authority;
	//车辆Id
	private String carId;
//	//公司名称（简称）
//	private String typeName;

//	public String getTypeName() {
//		return typeName;
//	}
//
//	public void setTypeName(String typeName) {
//		this.typeName = typeName;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

}
