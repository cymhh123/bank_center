package com.mdzy.bk.common.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipException;

/**
 * Created by Administrator on 2016/8/23.
 */
public class CompressUtil {

    /**
     * 压缩文件夹
     * @param zipPath 压缩生成的存放路径
     * @param dirPath 要压缩的目录
     * @throws net.lingala.zip4j.exception.ZipException
     */
    public static void makeZip(String zipPath,String dirPath) throws net.lingala.zip4j.exception.ZipException{
        ZipFile zipFile = new ZipFile(zipPath);
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        zipFile.addFolder(new File(dirPath), parameters);
    }

    public static void makeZip(String zipPath,ArrayList fileList,String rootFolder) throws net.lingala.zip4j.exception.ZipException{
        ZipFile zipFile = new ZipFile(zipPath);
        File file = zipFile.getFile();
        if(file.exists()){
            file.delete();
        }
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if(StringUtils.isNotBlank(rootFolder)){
            parameters.setRootFolderInZip(rootFolder);
        }
        zipFile.addFiles(fileList,parameters);
    }

    /**
     * 解压文件
     * @param zipPath
     * @param dirPath
     * @throws net.lingala.zip4j.exception.ZipException
     */
    public static void unZip(String zipPath,String dirPath) throws net.lingala.zip4j.exception.ZipException{
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.extractAll(dirPath);
    }
}
