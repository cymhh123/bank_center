/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.admin.service;


import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.modules.sys.service.CrudService;
import com.mdzy.bk.modules.yh.admin.dao.YhAdminBeanDao;
import com.mdzy.bk.modules.yh.admin.entity.YhAdminBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * yhadminService
 * @author chengyou
 * @version 2016-08-25
 */
@Service
public class YhAdminBeanService extends CrudService<YhAdminBeanDao, YhAdminBean> {
    /**
     * 添加管理员
     * @param adminBean
     */
    public void addAdmin(YhAdminBean adminBean){
        YhAdminBean param = new YhAdminBean();
        param.setAccount(adminBean.getAccount());
        List<YhAdminBean> list = this.findList(param);
        if(list != null && list.size() > 0){
            throw  new ServiceException("账号已存在");
        }
        this.save(adminBean);
    }
}