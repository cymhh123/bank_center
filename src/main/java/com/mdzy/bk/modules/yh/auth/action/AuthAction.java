package com.mdzy.bk.modules.yh.auth.action;

import com.github.pagehelper.PageInfo;
import com.mdzy.bk.modules.yh.auth.entity.AuthHistoryBean;
import com.mdzy.bk.modules.yh.auth.service.AuthHistoryBeanService;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
@Controller
@RequestMapping("${yhPath}/auth")
public class AuthAction {
    @Autowired
    private AuthHistoryBeanService authHistoryBeanService;

    /**
     * 列表首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yh/auth/list";
    }

    /**
     * 列表首页
     * @return
     */
    @RequestMapping("/curIndex")
    public String curIndex(){
        return "yh/auth/curlist";
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(AuthHistoryBean authHistoryBean,Integer page){
        List<AuthHistoryBean> list = this.authHistoryBeanService.findByPage(authHistoryBean,page);
        PageInfo<AuthHistoryBean> pageInfo = new PageInfo<>(list);
        return new ExecuteBean<List<AuthHistoryBean>>(list,pageInfo.getPages(), (int) pageInfo.getTotal());
    }

    /**
     * 当天列表
     * @return
     */
    @RequestMapping("/curlist")
    @ResponseBody
    public Object curlist(Integer tipStatus){
        List<AuthHistoryBean> list = this.authHistoryBeanService.operCurDay(tipStatus);
        return new ExecuteBean<List<AuthHistoryBean>>(list);
    }

    /**
     * 查询当天记录
     * @return
     */
    @RequestMapping("/todaylist")
    @ResponseBody
    public Object todayList(){
        AuthHistoryBean authHistoryBean = new AuthHistoryBean();
        authHistoryBean.setCreateDate(new Date());
        List<AuthHistoryBean> list = this.authHistoryBeanService.findList(authHistoryBean);
        return new ExecuteBean<>(list);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object del(String id){
        this.authHistoryBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
