package com.mdzy.bk.modules.yy.admin.action;

import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.common.utils.StringUtils;
import com.mdzy.bk.modules.yy.admin.entity.AdminBean;
import com.mdzy.bk.modules.yy.admin.service.AdminBeanService;
import com.mdzy.bk.modules.yy.bank.service.BankBeanService;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
@Controller
@RequestMapping("${yyPath}/admin")
public class AdminAction extends BaseController{
    @Autowired
    private AdminBeanService adminBeanService;

    /**
     * 列表首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yy/admin/list";
    }

    /**
     * 查询列表
     * @param AdminBean
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(AdminBean AdminBean){
        List<AdminBean> list = this.adminBeanService.findList(AdminBean);
        return new ExecuteBean<>(list);
    }

    /**
     * 编辑（添加或修改）
     * @param adminBean
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AdminBean adminBean){
        try {
            if(StringUtils.isBlank(adminBean.getId())){
                //添加
                this.adminBeanService.addAdmin(adminBean);
            }else {
                this.adminBeanService.update(adminBean);
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
        this.adminBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
