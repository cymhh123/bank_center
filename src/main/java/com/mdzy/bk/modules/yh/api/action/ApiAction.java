package com.mdzy.bk.modules.yh.api.action;

import com.mdzy.bk.common.config.Global;
import com.mdzy.bk.common.exception.ServiceException;
import com.mdzy.bk.common.utils.StringUtils;
import com.mdzy.bk.common.utils.SystemPath;
import com.mdzy.bk.modules.sys.action.BaseController;
import com.mdzy.bk.modules.yh.api.entity.ExecuteResult;
import com.mdzy.bk.modules.yh.auth.service.AuthHistoryBeanService;
import com.mdzy.bk.modules.yh.point.entity.PointBean;
import com.mdzy.bk.modules.yh.point.service.PointBeanService;
import com.mdzy.bk.modules.yh.yhescort.entity.YhEscortBean;
import com.mdzy.bk.modules.yh.yhescort.service.YhEscortBeanService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/23.
 */
@Controller
@RequestMapping("api")
public class ApiAction extends BaseController {
    @Autowired
    private YhEscortBeanService yhEscortBeanService;
    @Autowired
    private PointBeanService pointBeanService;
    @Autowired
    private AuthHistoryBeanService authHistoryBeanService;

    /**
     * 查询押运人员
     * @param cardNo 卡号
     * @return
     */
    @RequestMapping(value = "/escort",method = RequestMethod.GET)
    @ResponseBody
    public Object authPre(String cardNo,String deviceNo){
        if(StringUtils.isBlank(cardNo)){
            throw new ServiceException("入参有误");
        }
        try{
            YhEscortBean yhEscortBean = this.yhEscortBeanService.findByCardNo(cardNo);
            PointBean pointBean = this.pointBeanService.findByDeviceNo(deviceNo);
            return new ExecuteResult<>(yhEscortBean);
        }catch (ServiceException e){
            return new ExecuteResult<>("201",e.getMessage());
        }
    }

    /**
     * 认证
     * @param filedata
     * @param cardNo 卡号
     * @param deviceNo 设备号
     * @param result 认证结果（0失败，1成功）
     * @return
     */
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    @ResponseBody
    public Object auth(@RequestParam(value = "file", required = false) MultipartFile filedata,
                       String cardNo,String deviceNo,Integer result)throws IOException{
        //备份图片
        // 获取图片的文件名
        String fileName = filedata.getOriginalFilename();
        // 获取图片的扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 新的图片文件名
        String newFileName = new Date().getTime() + "." + extensionName;
        String filePath = SystemPath.getSysPath()+ File.separator+ Global.getTempYh()+File.separator+newFileName;
        FileUtils.copyInputStreamToFile(filedata.getInputStream(), new File(filePath));
        try{
            //保存数据
            this.authHistoryBeanService.saveAuthHistory(cardNo,deviceNo,result,newFileName);
        }catch (ServiceException e){
            return new ExecuteResult<>("201",e.getMessage());
        }
        return  new ExecuteResult<>();
    }
}
