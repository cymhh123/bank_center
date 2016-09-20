/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yy.admin.entity;

import com.mdzy.bk.modules.sys.entity.DataEntity;


/**
 * adminEntity
 * @author chengyou
 * @version 2016-08-18
 */
public class AdminBean extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String bankId;		// bank_id
	private String bankName;
	private String phone;
	private String account;
	private String password;
	
	public AdminBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}