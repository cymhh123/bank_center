/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.auth.service;

import com.mdzy.bk.common.config.Global;
import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.modules.yh.auth.dao.AuthHistoryBeanDao;
import com.mdzy.bk.modules.yh.auth.entity.AuthHistoryBean;
import com.mdzy.bk.modules.yh.point.entity.PointBean;
import com.mdzy.bk.modules.yh.point.service.PointBeanService;
import com.mdzy.bk.modules.sys.service.CrudService;
import com.mdzy.bk.modules.yh.yhescort.entity.YhEscortBean;
import com.mdzy.bk.modules.yh.yhescort.service.YhEscortBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * adminService
 * @author chengyou
 * @version 2016-08-22
 */
@Service
public class AuthHistoryBeanService extends CrudService<AuthHistoryBeanDao, AuthHistoryBean> {

    @Autowired
    private YhEscortBeanService yhEscortBeanService;
    @Autowired
    private PointBeanService pointBeanService;

    /**
     * 保存认证记录
     * @param cardNo
     * @param deviceNo
     */
    public void saveAuthHistory(String cardNo,String deviceNo,Integer result,String fileName){
        try{
            //cardNo找人
            YhEscortBean yhEscortBean = this.yhEscortBeanService.findByCardNo(cardNo);
            //deviceNo找网点
            PointBean pointBean = this.pointBeanService.findByDeviceNo(deviceNo);
            AuthHistoryBean authHistoryBean = new AuthHistoryBean();
            authHistoryBean.setDeviceNo(deviceNo);
            authHistoryBean.setStatus(result);
            authHistoryBean.setCardNo(cardNo);
            authHistoryBean.setPersion(yhEscortBean.getName());
            authHistoryBean.setPointName(pointBean.getName());
            authHistoryBean.setTipStatus(0);
            String imgUrl = Global.getAppPathForYH() + "/" + Global.getTempYh() + "/" + fileName;
            authHistoryBean.setImgUrl(imgUrl);
            this.save(authHistoryBean);
        }catch (ServiceException e){
            throw new ServiceException(e);
        }
    }

    /**
     * 实时认证
     * @return
     */
    public List<AuthHistoryBean> operCurDay(Integer tipStatus){
        //查询当日未读认证
        AuthHistoryBean authHistoryBean = new AuthHistoryBean();
        authHistoryBean.setTipStatus(tipStatus);
        authHistoryBean.setCreateDate(new Date());
        List<AuthHistoryBean> list = this.findList(authHistoryBean);
        //更新为已读
        if(list != null && list.size() > 0){
            this.dao.batchUpdTipStatus(list);
        }
        return list;
    }
}