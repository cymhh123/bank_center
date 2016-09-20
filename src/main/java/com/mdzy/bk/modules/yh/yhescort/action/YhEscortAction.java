package com.mdzy.bk.modules.yh.yhescort.action;

import com.mdzy.bk.common.config.Global;
import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.common.poi.ExcelUtil;
import com.mdzy.bk.common.utils.CompressUtil;
import com.mdzy.bk.common.utils.SystemPath;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.sys.entity.ExecuteBean;
import com.mdzy.bk.modules.yh.yhescort.entity.YhEscortBean;
import com.mdzy.bk.modules.yh.yhescort.service.YhEscortBeanService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
@Controller
@RequestMapping("${yhPath}/yhescort")
public class YhEscortAction extends BaseController {
    @Autowired
    private YhEscortBeanService yhEscortBeanService;

    /**
     * 列表页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "yh/yhescort/list";
    }

    /**
     * 查询列表
     * @param yhEscortBean
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(YhEscortBean yhEscortBean){
        List<YhEscortBean> list = this.yhEscortBeanService.findList(yhEscortBean);
        return new ExecuteBean<>(list);
    }


    /**
     * 导入数据
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Object exportZip(@RequestParam(value = "file", required = false)MultipartFile filedata) throws Exception{
        List<YhEscortBean> list = null;
        if (filedata != null && !filedata.isEmpty()) {
            // 获取文件名
            String fileName = filedata.getOriginalFilename();
            // 获取文件扩展名
            String extensionName = fileName.substring(fileName.lastIndexOf(".")+1);
            // 新的文件名
            String newFileName = "temp."+extensionName;
            String savePath = SystemPath.getSysPath()+File.separator+newFileName;
            //删除压缩文件
            FileUtils.deleteQuietly(new File(savePath));
            //保存上传的压缩文件
            FileUtils.copyInputStreamToFile(filedata.getInputStream(),new File(savePath));
            //解压
            CompressUtil.unZip(savePath,SystemPath.getSysPath());
            //分析数据
            //数据路径
            String dataPath = SystemPath.getSysPath() + File.separator + Global.getDataYh() + File.separator + "data.xls";
            List<Object> objects = ExcelUtil.getInstance().readExcel2ObjsByPath(dataPath,YhEscortBean.class);
            list = this.yhEscortBeanService.toDatabaseFromExcel(objects);
        }else {
            throw new ServiceException("文件导入失败");
        }
        return list;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object del(String id){
        this.yhEscortBeanService.delete(id);
        return new ExecuteBean<>();
    }
}
