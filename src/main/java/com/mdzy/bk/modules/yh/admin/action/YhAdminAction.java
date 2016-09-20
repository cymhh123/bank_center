package com.mdzy.bk.modules.yh.admin.action;

import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.common.utils.StringUtils;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import com.mdzy.bk.modules.yh.admin.entity.YhAdminBean;
import com.mdzy.bk.modules.yh.admin.service.YhAdminBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
@Controller
@RequestMapping("${yhPath}/admin")
public class YhAdminAction extends BaseController {
    @Autowired
    private YhAdminBeanService yhAdminBeanService;

    /**
     * 银行列表页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yh/admin/list";
    }

    /**
     * 查询列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(YhAdminBean yhAdminBean){
        List<YhAdminBean> list = this.yhAdminBeanService.findList(yhAdminBean);
        return new ExecuteBean<>(list);
    }

    /**
     * 编辑（添加或修改）
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(YhAdminBean yhAdminBean){
        try{
            if(StringUtils.isBlank(yhAdminBean.getId())){
                //添加
                this.yhAdminBeanService.addAdmin(yhAdminBean);
            }else {
                this.yhAdminBeanService.update(yhAdminBean);
            }
        }catch (ServiceException e){
            return new ExecuteBean<>("201",e.getMessage());
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
        this.yhAdminBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
