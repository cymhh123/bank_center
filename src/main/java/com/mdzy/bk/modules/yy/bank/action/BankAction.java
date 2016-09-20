package com.mdzy.bk.modules.yy.bank.action;

import com.mdzy.bk.common.utils.StringUtils;
import com.mdzy.bk.modules.yy.admin.entity.AdminBean;
import com.mdzy.bk.modules.yy.bank.entity.BankBean;
import com.mdzy.bk.modules.yy.bank.service.BankBeanService;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 银行控制器
 * Created by Administrator on 2016/8/18.
 */
@Controller
@RequestMapping("${yyPath}/bank")
public class BankAction extends BaseController {
    @Autowired
    private BankBeanService bankBeanService;

    /**
     * 银行列表页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yy/bank/list";
    }

    /**
     * 查询银行列表
     * @param bankBean
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(BankBean bankBean, HttpSession session){
        //登录者绑定银行
        AdminBean adminBean = super.getAdminBeanFromSession(session);
        bankBean.setId(adminBean.getBankId());
        List<BankBean> list = this.bankBeanService.findList(bankBean);
        return new ExecuteBean<>(list);
    }

    /**
     * 编辑（添加或修改）
     * @param bankBean
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(BankBean bankBean){
        if(StringUtils.isBlank(bankBean.getId())){
            //添加
            this.bankBeanService.save(bankBean);
        }else {
            this.bankBeanService.update(bankBean);
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
        this.bankBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
