/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yy.escort.entity;

import com.mdzy.bk.common.poi.ExcelResources;
import com.mdzy.bk.modules.sys.entity.DataEntity;


/**
 * adminEntity
 * @author chengyou
 * @version 2016-08-19
 */
public class EscortBean extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String idcard;		// idcard
	private String phone;		// phone
	private String cardNo;		// card_no
	private String imgUrl;		// img_url
	private String bankId;		// bank_id
	private String bankName;
	private String yyDate;
	
	public EscortBean() {
		super();
	}

	@ExcelResources(title="押运员姓名",order=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelResources(title="身份证号",order=2)
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@ExcelResources(title="电话",order=3)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ExcelResources(title="卡号",order=4)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@ExcelResources(title = "照片路径",order=8)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@ExcelResources(title="银行ID",order=5)
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@ExcelResources(title="所属银行",order=6)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@ExcelResources(title="生成时间",order=7)
	public String getYyDate() {
		return yyDate;
	}

	public void setYyDate(String yyDate) {
		this.yyDate = yyDate;
	}
	
}