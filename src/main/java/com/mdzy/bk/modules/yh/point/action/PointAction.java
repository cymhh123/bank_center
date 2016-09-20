package com.mdzy.bk.modules.yh.point.action;

import com.mdzy.bk.common.utils.StringUtils;
import com.mdzy.bk.modules.yh.point.entity.PointBean;
import com.mdzy.bk.modules.yh.point.service.PointBeanService;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
@Controller
@RequestMapping("${yhPath}/point")
public class PointAction extends BaseController {
    @Autowired
    private PointBeanService pointBeanService;

    /**
     * 银行列表页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yh/point/list";
    }

    /**
     * 查询银行列表
     * @param pointBean
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(PointBean pointBean){
        List<PointBean> list = this.pointBeanService.findList(pointBean);
        return new ExecuteBean<>(list);
    }

    /**
     * 编辑（添加或修改）
     * @param pointBean
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(PointBean pointBean){
        if(StringUtils.isBlank(pointBean.getId())){
            //添加
            this.pointBeanService.save(pointBean);
        }else {
            this.pointBeanService.update(pointBean);
        }
        return new ExecuteBean<>();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object del(String id){
        this.pointBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
