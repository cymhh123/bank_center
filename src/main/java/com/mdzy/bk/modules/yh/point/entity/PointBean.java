/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.point.entity;

import com.mdzy.bk.modules.sys.entity.DataEntity;


/**
 * adminEntity
 * @author chengyou
 * @version 2016-08-22
 */
public class PointBean extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String address;		// address
	private String phone;		// phone
	private String persion;		// persion
	private String deviceNo;		// device_no

	public PointBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPersion() {
		return persion;
	}

	public void setPersion(String persion) {
		this.persion = persion;
	}
	
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
}