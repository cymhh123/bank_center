package com.mdzy.bk.common.config;


import com.mdzy.bk.common.utils.MD5;
import com.mdzy.bk.common.utils.PropertiesLoader;
import com.mdzy.bk.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 * 缓存并获取resources配置文件的信息
 * @author chengyou
 * @version 2014-06-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<String,String>();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("resources.properties");

	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('product_path')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 银行项目路劲
	 * @see ${fns:getConfig('product_path')}
	 */
	public static String getAppPathForYH() {
		return getConfig("app_path_yh");
	}

	/**
	 * 押运中心项目路径
	 * @see ${fns:getConfig('')}
	 */
	public static String getAppPathForYY() {
		return getConfig("app_path_yy");
	}

	/**
	 * 押运中心图片存放路径
	 * @return
     */
	public static String getImgYy(){
		return getConfig("img_yy");
	}

	/**
	 * 银行端上传数据存放路径
	 * @return
     */
	public static String getDataYh(){
		return getConfig("data_yh");
	}

	/**
	 * 银行端认证记录照片存放路径
	 * @return
     */
	public static String getTempYh(){
		return getConfig("temp_yh");
	}

	public static String getYyPath(){
		return getConfig("yyPath");
	}

	public static String getYhPath(){
		return getConfig("yhPath");
	}

	public static String getCurrApp(){
		return getConfig("curr_app");
	}
	/**
	 * 加密校验
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean insecurity(String str1,String str2){
		String salt = getConfig("ecode");
		StringBuffer sb = new StringBuffer(salt);
		String code= MD5.md5(sb.append(str1).toString());
		return !code.equals(str2);
	}
}
