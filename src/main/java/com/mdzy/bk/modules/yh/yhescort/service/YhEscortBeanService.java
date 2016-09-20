/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mdzy.bk.modules.yh.yhescort.service;

import java.util.List;

import com.mdzy.bk.common.config.Global;
import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.modules.sys.service.CrudService;
import com.mdzy.bk.modules.yh.yhescort.dao.YhEscortBeanDao;
import com.mdzy.bk.modules.yh.yhescort.entity.YhEscortBean;
import org.springframework.stereotype.Service;

/**
 * adminService
 * @author chengyou
 * @version 2016-08-22
 */
@Service
public class YhEscortBeanService extends CrudService<YhEscortBeanDao, YhEscortBean> {

	/**
	 * 从excel导入数据
	 * @param list
	 * @return
     */
	public List<YhEscortBean> toDatabaseFromExcel(List<Object> list){
		//删除数据
		this.dao.deleteAll();
		//插入数据
		for(int i = 0; i < list.size(); i++){
			YhEscortBean yhEscortBean = (YhEscortBean) list.get(i);
			//替换img路径
			String imgPath = yhEscortBean.getImgUrl().replace(Global.getImgYy(),Global.getDataYh());
			yhEscortBean.setImgUrl(imgPath);
			this.save(yhEscortBean);
		}
		return this.dao.findList(new YhEscortBean());
	}

	/**
	 * 查询押运员
	 * @param cardNo 卡号
	 * @return
     */
	public YhEscortBean findByCardNo(String cardNo){
		YhEscortBean yhEscortBean = new YhEscortBean();
		yhEscortBean.setCardNo(cardNo);
		List<YhEscortBean> list = this.findList(yhEscortBean);
		if(list.size() > 0){
			return list.get(0);
		}else{
			throw new ServiceException("未查询到押运人员");
		}
	}

}