/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.admin.entity;

import com.mdzy.bk.modules.sys.entity.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * yhadminEntity
 * @author chengyou
 * @version 2016-08-25
 */
public class YhAdminBean extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private String account;		// account
	private String name;		// name
	private String password;		// password
	private String phone;
	
	public YhAdminBean() {
		super();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}