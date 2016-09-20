/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.auth.dao;

import com.mdzy.bk.common.persistence.CrudDao;
import com.mdzy.bk.common.persistence.annotation.MyBatisDao;
import com.mdzy.bk.modules.yh.auth.entity.AuthHistoryBean;

import java.util.List;

/**
 * adminDAO接口
 * @author chengyou
 * @version 2016-08-22
 */
@MyBatisDao
public interface AuthHistoryBeanDao extends CrudDao<AuthHistoryBean> {
	public void batchUpdTipStatus(List<AuthHistoryBean> list);
}