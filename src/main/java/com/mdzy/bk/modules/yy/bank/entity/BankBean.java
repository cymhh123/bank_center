/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yy.bank.entity;

import com.mdzy.bk.modules.sys.entity.DataEntity;


/**
 * adminEntity
 * @author chengyou
 * @version 2016-08-18
 */
public class BankBean extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String persion;		// persion
	private String phone;		// phone

	public BankBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPersion() {
		return persion;
	}

	public void setPersion(String persion) {
		this.persion = persion;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}