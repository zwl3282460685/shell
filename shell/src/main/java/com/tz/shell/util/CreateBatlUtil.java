package com.tz.shell.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 生成bat脚本
 * @author zwl
 * @date : 2021/2/18 10:24
 */
@Component
public class CreateBatlUtil {
    private static String backPath = "D:\\TA\\backup";
    private static String time = new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * 创建迁移的bat脚本
     * @param paths 迁移内容
     * @param targetClassPath 目标环境classes文件夹所在的路径
     * @param targetJsPath 目标环境statics 文件夹所在的路径
     */
    public static String createTransFerBat(List<String> paths, String targetClassPath, String targetJsPath) {
        StringBuffer transferSb = new StringBuffer();
        transferSb.append("@echo off").append("\n");
        transferSb.append("set transferPath=C:\\TA\\").append("\r\n");
        transferSb.append("set targetClassPath=").append(targetClassPath).append("\r\n");
        transferSb.append("set targetJsPath=").append(targetJsPath).append("\r\n");
        //transferSb.append("cd %transferPath%").append(time).append("\n");
        for(String path : paths){
            if(path.endsWith(".class") || path.endsWith(".html") || path.endsWith(".sql") || path.endsWith(".xml")){
                String classFolderPath = path.substring(0, path.lastIndexOf("\\"));
                transferSb.append("if exist %targetClassPath%").append(classFolderPath).append(" (").append("\r\n");
                transferSb.append("   copy %transferPath%").append(time).append(path).append(" %targetClassPath%").append(classFolderPath).append("\r\n");
                transferSb.append(") else (").append("\r\n");
                transferSb.append("   md %targetClassPath%").append(classFolderPath).append("\r\n");
                transferSb.append("   copy %transferPath%").append(time).append(path).append(" %targetClassPath%").append(classFolderPath).append("\r\n");
                transferSb.append(")").append("\r\n\r\n");
            }else{
                String jsFolderPath = path.substring(0, path.lastIndexOf("\\"));
                transferSb.append("if exits %targetJsPath%").append(jsFolderPath).append(" (").append("\r\n");
                transferSb.append("   copy %transferPath%").append(time).append(path).append(" %targetJsPath%").append(jsFolderPath).append("\r\n");
                transferSb.append(") else (").append("\r\n");
                transferSb.append("   md %targetClassPath%").append(jsFolderPath).append("\r\n");
                transferSb.append("   copy %transferPath%").append(time).append(path).append(" %targetJsPath%").append(jsFolderPath).append("\r\n");
                transferSb.append(")").append("\r\n\r\n");
            }
        }
        transferSb.append("pause");
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
        DBsb.append("@echo off").append("\n");
        DBsb.append("set backPath=").append(backPath).append("\\").append(time);
        DBsb.append("\r\n");
        DBsb.append("set classPath=").append(targetClassPath).append("\r\n");
        DBsb.append("set jsPath=").append(targetJSPath).append("\r\n");
        DBsb.append("md %backPath%");
        DBsb.append("\r\n");

        for(String path : paths){
            if(path.endsWith(".class") || path.endsWith(".html") || path.endsWith(".sql") || path.endsWith(".xml")){
                String classFolderPath = path.substring(0, path.lastIndexOf("\\"));
                DBsb.append("if exist %classPath%").append(path).append(" (").append("\r\n");
                DBsb.append("   md %backPath%").append(classFolderPath).append("\r\n"); //创建文件夹
                DBsb.append("   copy ").append(targetClassPath).append(path).append(" %backPath%").append(classFolderPath).append("\r\n");
                DBsb.append(")").append("\r\n\r\n");
            }else{
                String jsFolderPath = path.substring(0, path.lastIndexOf("\\"));
                DBsb.append("if exist %jsPath%").append(path).append(" (").append("\r\n");
                DBsb.append("   md %backPath%").append(jsFolderPath).append("\r\n"); //创建文件夹
                DBsb.append("   copy ").append(targetJSPath).append(path).append(" ").append("%backPath%").append(jsFolderPath).append("\r\n");
                DBsb.append(")").append("\r\n\r\n");
            }
        }
        return DBsb.toString();
    }
}
