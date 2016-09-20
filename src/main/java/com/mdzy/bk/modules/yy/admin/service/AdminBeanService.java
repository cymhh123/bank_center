/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yy.admin.service;

import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.modules.yy.admin.dao.AdminBeanDao;
import com.mdzy.bk.modules.yy.admin.entity.AdminBean;
import com.mdzy.bk.modules.sys.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * adminService
 * @author chengyou
 * @version 2016-08-18
 */
@Service
public class AdminBeanService extends CrudService<AdminBeanDao, AdminBean> {

    /**
     * 添加管理员
     * @param adminBean
     */
    public void addAdmin(AdminBean adminBean){
        AdminBean param = new AdminBean();
        param.setAccount(adminBean.getAccount());
        List<AdminBean> list = this.findList(param);
        if(list != null && list.size() > 0){
            throw  new ServiceException("账户已存在");
        }
        this.save(adminBean);
    }
}