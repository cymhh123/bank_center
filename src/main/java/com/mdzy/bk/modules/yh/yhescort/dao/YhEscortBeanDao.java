/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.yhescort.dao;


import com.mdzy.bk.common.persistence.CrudDao;
import com.mdzy.bk.common.persistence.annotation.MyBatisDao;
import com.mdzy.bk.modules.yh.yhescort.entity.YhEscortBean;

/**
 * adminDAO接口
 * @author chengyou
 * @version 2016-08-22
 */
@MyBatisDao
public interface YhEscortBeanDao extends CrudDao<YhEscortBean> {
    void deleteAll();
}