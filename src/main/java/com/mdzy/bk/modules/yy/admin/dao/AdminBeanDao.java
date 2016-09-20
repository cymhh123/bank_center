/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yy.admin.dao;


import com.mdzy.bk.common.persistence.CrudDao;
import com.mdzy.bk.common.persistence.annotation.MyBatisDao;
import com.mdzy.bk.modules.yy.admin.entity.AdminBean;

/**
 * adminDAO接口
 * @author chengyou
 * @version 2016-08-18
 */
@MyBatisDao
public interface AdminBeanDao extends CrudDao<AdminBean> {
	
}