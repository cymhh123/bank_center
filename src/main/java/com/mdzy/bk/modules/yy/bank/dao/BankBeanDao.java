/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yy.bank.dao;


import com.mdzy.bk.common.persistence.CrudDao;
import com.mdzy.bk.common.persistence.annotation.MyBatisDao;
import com.mdzy.bk.modules.yy.bank.entity.BankBean;

/**
 * adminDAO接口
 * @author chengyou
 * @version 2016-08-18
 */
@MyBatisDao
public interface BankBeanDao extends CrudDao<BankBean> {
	
}