package com.mdzy.bk.modules.yh.index.action;

import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import com.mdzy.bk.modules.yh.admin.entity.YhAdminBean;
import com.mdzy.bk.modules.yh.admin.service.YhAdminBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
@Controller
@RequestMapping("${yhPath}")
public class YhIndexAction extends BaseController{
    @Autowired
    private YhAdminBeanService yhAdminBeanService;

    /**
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yh/index";
    }

    @RequestMapping("/login_page")
    public String login_form(){
        return "yh/login";
    }

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/login_valid")
    @ResponseBody
    public Object login(String account, String password, HttpSession session){
        //查询用户
        YhAdminBean adminBean = new YhAdminBean();
        adminBean.setAccount(account);
        adminBean.setPassword(password);
        List<YhAdminBean> adminBeanList = this.yhAdminBeanService.findList(adminBean);
        if(adminBeanList == null || adminBeanList.size() == 0){
            return new ExecuteBean<>("201","用户名或密码错误");
        }
        adminBean = adminBeanList.get(0);
        session.setAttribute("admin",adminBean);
        return new ExecuteBean<>();
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/out")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:"+yhPath+"/login_page";
    }
}
