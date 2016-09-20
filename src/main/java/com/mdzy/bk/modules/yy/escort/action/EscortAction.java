package com.mdzy.bk.modules.yy.escort.action;

import com.mdzy.bk.common.config.Global;
import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.common.poi.ExcelUtil;
import com.mdzy.bk.common.utils.CompressUtil;
import com.mdzy.bk.common.utils.StringUtils;
import com.mdzy.bk.common.utils.SystemPath;
import com.mdzy.bk.modules.yy.admin.entity.AdminBean;
import com.mdzy.bk.modules.yy.escort.entity.EscortBean;
import com.mdzy.bk.modules.yy.escort.service.EscortBeanService;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
@Controller
@RequestMapping("${yyPath}/escort")
public class EscortAction extends BaseController {
    @Autowired
    private EscortBeanService escortBeanService;

    /**
     * 银行列表页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yy/escort/list";
    }

    /**
     * 查询列表
     * @param escortBean
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(EscortBean escortBean, HttpSession session){
        //登录者绑定银行
        AdminBean adminBean = super.getAdminBeanFromSession(session);
        escortBean.setBankId(adminBean.getBankId());
        List<EscortBean> list = this.escortBeanService.findList(escortBean);
        return new ExecuteBean<>(list);
    }

    /**
     * 编辑（添加或修改）
     * @param escortBean
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(@RequestParam(value = "file", required = false) MultipartFile filedata, EscortBean escortBean, HttpServletRequest request) throws Exception{
        // 文件操作
        if (filedata != null && !filedata.isEmpty()) {
            // 获取图片的文件名
            String fileName = filedata.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 身份证号+"."图片扩展名
            String newFileName = escortBean.getIdcard() + "." + extensionName;
            //.../imgs/370105198801192567.png
            String imgUrl = Global.getAppPathForYY() + "/"+Global.getImgYy() + "/" + newFileName;
            escortBean.setImgUrl(imgUrl);
            //读取数据库，看图片是否存在，存在则先删除
            String desPath = SystemPath.getSysPath() + File.separator + Global.getImgYy() + File.separator + newFileName;
            //删除
            FileUtils.deleteQuietly(new File(desPath));
            //保存
            FileUtils.copyInputStreamToFile(filedata.getInputStream(),new File(desPath));
        }
        //数据操作
        if(StringUtils.isBlank(escortBean.getId())){
            //添加
            this.escortBeanService.save(escortBean);
        }else {
            this.escortBeanService.update(escortBean);
        }
        return new ExecuteBean<>();
    }

    /**
     * zip打包下载
     * @param
     */
    @RequestMapping("/down")
    private String downFile(EscortBean escortBean,HttpServletResponse response) throws Exception {
        List<EscortBean> list = this.escortBeanService.findList(escortBean);
        //图片基础路径(/imgs/)
        String imgBasePath = SystemPath.getSysPath() + File.separator + Global.getImgYy() + File.separator;
        //查询出要打包的图片文件
        ArrayList imgFileList = new ArrayList();
        for(int i=0; i<list.size(); i++){
            String imgUrl = list.get(i).getImgUrl();
            String fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1);
            String imgPath = imgBasePath + fileName;
            imgFileList.add(new File(imgPath));
        }
        //打包的excel
        String dataFilePath = SystemPath.getSysPath() + File.separator + "data.xls";
        ExcelUtil.getInstance().exportObj2Excel(dataFilePath,list,EscortBean.class,false);
        imgFileList.add(new File(dataFilePath));

        String tmpFileName = "data.zip";
        String strZipPath = SystemPath.getSysPath() + File.separator + tmpFileName;
        //打包
        CompressUtil.makeZip(strZipPath,imgFileList,Global.getDataYh());

        //下载
        InputStream ins = new FileInputStream(strZipPath);
        BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
        OutputStream outs = response.getOutputStream();// 获取文件输出IO流
        BufferedOutputStream bouts = new BufferedOutputStream(outs);
        try {
            response.setContentType("application/x-download");// 设置response内容的类型
            response.setHeader(
                    "Content-disposition",
                    "attachment;filename="
                            + URLEncoder.encode(tmpFileName, "UTF-8"));// 设置头部信息
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            // 开始向网络传输文件流
            while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                bouts.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new ServiceException("打包下载异常",e);
        } finally {
            bouts.flush();// 这里一定要调用flush()方法
            ins.close();
            bins.close();
            outs.close();
            bouts.close();
        }
        return null;
    }

    /**
     * 导出excel
     * @param escortBean
     * @param response
     * @return
     */
    @RequestMapping("/excel")
    public String export2Excel(EscortBean escortBean,HttpServletResponse response){
        try {
            //查询列表
            List<EscortBean> list = this.escortBeanService.findList(escortBean);
            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=escort.xls");
            OutputStream os = response.getOutputStream();
            ExcelUtil.getInstance().exportObj2Excel(os,list,EscortBean.class,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object del(String id){
        this.escortBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
