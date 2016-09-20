/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.log.dao;

import com.mdzy.bk.common.persistence.annotation.MyBatisDao;
import com.mdzy.bk.modules.log.entity.LogBean;


/**
 * 日志DAO接口
 * @author chengyou
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao {
    int insert(LogBean logBean);
}
