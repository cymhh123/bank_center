/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.point.service;

import java.util.List;

import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.modules.yh.point.dao.PointBeanDao;
import com.mdzy.bk.modules.yh.point.entity.PointBean;
import com.mdzy.bk.modules.sys.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * adminService
 * @author chengyou
 * @version 2016-08-22
 */
@Service
public class PointBeanService extends CrudService<PointBeanDao, PointBean> {

    /**
     * 查找网点信息
     * @param deviceNo 设备号
     * @return
     */
	public PointBean findByDeviceNo(String deviceNo){
        PointBean pointBean = new PointBean();
        pointBean.setDeviceNo(deviceNo);
        List<PointBean> list = this.findList(pointBean);
        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            throw new ServiceException("根据设备未查到对应网点信息");
        }
    }
}