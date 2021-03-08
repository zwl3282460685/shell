package com.tz.shell.util;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 生成shell脚本
 * @author zwl
 * @date : 2021/2/18 10:24
 */
@Component
public class CreateShellUtil {
    private static String backPath = "/home/backup/";
    private static String time = new SimpleDateFormat("yyyyMMdd").format(new Date());


    /**
     * 创建获取迁移文件的shell脚本
     * @param paths 迁移文件的相对路径
     * @param sourceClassPath 服务器中classes文件夹所在的路径
     * @param sourceJsPath  服务器中statics文件夹所在路径
     */
    public static String getFileShell(List<String> paths, String sourceClassPath, String sourceJsPath){
        StringBuffer getSb = new StringBuffer();
        String filePath = "\"/home/transfer/" + time + "\"";
        getSb.append("#!/bin/bash").append("\n");
        getSb.append("filePath=").append(filePath).append("\n");
        getSb.append("sourcePath=\"").append(sourceClassPath).append("\"").append("\n");
        getSb.append("mkdir -p ${filePath}").append("\n");
        getSb.append("cd ${filePath}").append("\n");
        for(String path : paths){
            if(path.endsWith(".class") || path.endsWith(".html") || path.endsWith(".sql") || path.endsWith(".xml") || path.startsWith("/com")){
                String classFolderPath = path.substring(0, path.lastIndexOf("/"));
                getSb.append("mkdir -p ${filePath}").append(classFolderPath).append("\n");
                getSb.append("cp -r ").append(sourceClassPath).append(path).append(" ${filePath}").append(classFolderPath).append("\n");

            }else{
                String jsFolderPath = path.substring(0, path.lastIndexOf("/"));
                getSb.append("mkdir -p ${filePath}").append(jsFolderPath).append("\n");
                getSb.append("cp -r ").append(sourceJsPath).append(path).append(" ${filePath}").append(jsFolderPath).append("\n");
            }
        }
        return getSb.toString();
    }

    /**
     * 创建迁移的shell脚本
     * @param paths 迁移内容
     * @param targetClassPath 目标环境classes文件夹所在的路径
     * @param targetJsPath 目标环境statics 文件夹所在的路径
     */
    public static String createTransFerShell(List<String> paths, String targetClassPath, String targetJsPath) {
        StringBuffer transferSb = new StringBuffer();
        transferSb.append("#!/bin/bash").append("\n");
        transferSb.append("transferPath=\"/home/transfer\"").append("\n");
        transferSb.append("targetClassPath=\"").append(targetClassPath).append("\"").append("\n");
        transferSb.append("targetJsPath=\"").append(targetJsPath).append("\"").append("\n");
        transferSb.append("cd ${transferPath}/").append(time).append("\n");
        for(String path : paths){
            if(path.endsWith(".class") || path.endsWith(".html") || path.endsWith(".sql") || path.endsWith(".xml") || path.startsWith("/com")){
                String classFolderPath = path.substring(0, path.lastIndexOf("/"));
                transferSb.append("if [ -d ${targetClassPath}").append(classFolderPath).append(" ]; then").append("\n");
                transferSb.append("   cp -r ${transferPath}/").append(time).append(path).append(" ${targetClassPath}").append(classFolderPath).append("\n");
                transferSb.append("else").append("\n");
                transferSb.append("   mkdir -p ${targetClassPath}").append(classFolderPath).append("\n");
                transferSb.append("   cp -r ${transferPath}").append(path).append(" ${targetClassPath}").append(classFolderPath).append("\n");
                transferSb.append("fi").append("\n\n");
            }else{
                String jsFolderPath = path.substring(0, path.lastIndexOf("/"));
                transferSb.append("if [ -d ${targetJsPath}").append(jsFolderPath).append(" ]; then").append("\n");
                transferSb.append("   cp -r ${transferPath}").append(path).append(" ${targetJsPath}").append(jsFolderPath).append("\n");
                transferSb.append("else").append("\n");
                transferSb.append("   mkdir -p ${targetClassPath}").append(jsFolderPath).append("\n");
                transferSb.append("   cp -r ${transferPath}").append(time).append(path).append(" ${targetJsPath}").append(jsFolderPath).append("\n");
                transferSb.append("fi").append("\n\n");
            }
        }
        return transferSb.toString();
    }

    /**
     * 生成备份的shell脚本
     * @param paths //备份文件的具体内容
     * @param targetClassPath //目标环境的class文件的相对路径
     * @param targetJSPath    //目标环境的js文件的相对路径
     */
    public static String createDBShell(List<String> paths, String targetClassPath, String targetJSPath){
        StringBuffer DBsb = new StringBuffer();
        DBsb.append("#!/bin/bash").append("\n");
        DBsb.append("backPath=\"").append(backPath).append(time).append("\"");
        DBsb.append("\n");
        DBsb.append("classPath=\"").append(targetClassPath).append("\"").append("\n");
        DBsb.append("jsPath=\"").append(targetJSPath).append("\"").append("\n");
        DBsb.append("mkdir -p ${backPath}");
        DBsb.append("\n");

        for(String path : paths){
            if(path.endsWith(".class") || path.endsWith(".html") || path.endsWith(".sql") || path.endsWith(".xml") || path.startsWith("/com")){
                String classFolderPath = path.substring(0, path.lastIndexOf("/"));
                DBsb.append("if [ -f ${classPath}").append(path).append(" ];then").append("\n");
                DBsb.append("   mkdir -p ${backPath}").append(classFolderPath).append("\n"); //创建文件夹
                DBsb.append("   cp -r ").append(targetClassPath).append(path).append(" ").append("${backPath}").append(classFolderPath).append("\n");
                DBsb.append("fi").append("\n\n");
            }else{
                String jsFolderPath = path.substring(0, path.lastIndexOf("/"));
                DBsb.append("if [ -f ${jsPath}").append(path).append(" ];then").append("\n");
                DBsb.append("   mkdir -p ${backPath}").append(jsFolderPath).append("\n"); //创建文件夹
                DBsb.append("   cp -r ").append(targetJSPath).append(path).append(" ").append("${backPath}").append(jsFolderPath).append("\n");
                DBsb.append("fi").append("\n\n");
            }
        }
        return DBsb.toString();
    }
}
