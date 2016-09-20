/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.auth.entity;

import com.mdzy.bk.modules.sys.entity.DataEntity;

/**
 * adminEntity
 * @author chengyou
 * @version 2016-08-22
 */
public class AuthHistoryBean extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private String persion;		// persion
	private String cardNo;		// card_no
	private String pointName;		// point_name
	private String deviceNo;		// device_no
	private Integer status;		// status
	private Integer tipStatus;		// tip_status
	private String imgUrl;

	public AuthHistoryBean() {
		super();
	}

	public String getPersion() {
		return persion;
	}

	public void setPersion(String persion) {
		this.persion = persion;
	}
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getTipStatus() {
		return tipStatus;
	}

	public void setTipStatus(Integer tipStatus) {
		this.tipStatus = tipStatus;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}