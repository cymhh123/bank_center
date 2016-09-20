package com.mdzy.bk.modules.yy.index.action;

import com.mdzy.bk.modules.yy.admin.entity.AdminBean;
import com.mdzy.bk.modules.yy.admin.service.AdminBeanService;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/8/10.
 */
@Controller
@RequestMapping("${yyPath}")
public class IndexAction extends BaseController {
    @Autowired
    private AdminBeanService adminBeanService;

    /**
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        AdminBean adminBean = super.getAdminBeanFromSession(session);
        model.addAttribute("admin",adminBean);
        return "yy/index";
    }

    @RequestMapping("/login_page")
    public String login_form(){
        return "yy/login";
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
        AdminBean adminBean = new AdminBean();
        adminBean.setAccount(account);
        adminBean.setPassword(password);
        List<AdminBean> adminBeanList = this.adminBeanService.findList(adminBean);
        if(adminBeanList == null || adminBeanList.size() == 0){
            return new ExecuteBean<>("201","用户名或密码错误");
        }
        adminBean = adminBeanList.get(0);
        session.setAttribute("admin",adminBean);
        return new ExecuteBean<>();
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "yy/welcome";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/out")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:"+yyPath+"/login_page";
    }

}
