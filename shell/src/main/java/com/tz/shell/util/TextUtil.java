package com.tz.shell.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文本工具类
 * @author zwl
 * @date : 2021/2/18 10:04
 */
public class TextUtil {
    //private static String time = new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * 写入文件
     * @param textPath
     * @param textName
     * @param data
     * @return
     */
    public static boolean writeText(String textPath, String textName, String data){
        boolean flag = false;
        File filePath = new File(textPath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(textPath + File.separator + textName);
            fw.write(data);
            System.out.println("write " + textName + " OK");
            flag = true;
        } catch (IOException e) {
            System.out.println("write " + textName + " false");
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 生成文件流
     * @param data
     * @return
     */
    public static ResponseEntity<byte[]> writeText2(String data){
        String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName = "db" + time + ".sh";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos.write(data.getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * 读取文件
     * @param textPath
     * @param textName
     * @return
     */
    public static List<String> readText(String textPath, String textName){
        File file = new File(textPath, textName);
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                if(!line.startsWith("#")){
                    list.add(line);
                }
                line = reader.readLine();
            }
            return list;
        } catch (IOException e) {
            System.out.println("读取" + textName + " 失败！");
            e.printStackTrace();
            return null;
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取传入的文件
     * @param multipartFile
     * @return
     */
    public static List<String> readText2(MultipartFile multipartFile){
        List<String> list2 = new ArrayList<>();
        File file = transferToFile(multipartFile);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                if(!line.startsWith("#")){
                    list2.add(line);
                }
                line = reader.readLine();
            }
            return list2;
        } catch (IOException e) {
            System.out.println("读取文件失败！");
            e.printStackTrace();
            return null;
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 新建文件夹
     * @param path
     * @return
     */
    public static String  mkdir(String path){
        StringBuffer sb = new StringBuffer();
        sb.append("myPath=" + path);
        sb.append("\n");
        sb.append("if [ -d $myPath ] ; then");
        sb.append("\n");
        sb.append("echo $myPath\"文件夹存在！\"");
        sb.append("\n");
        sb.append("else");
        sb.append("\n");
        // sb.append("cd "+path.substring(0, path.lastIndexOf("/")));
        sb.append("\n");
        sb.append("mkdir " + path);
        sb.append("\n");
        sb.append("fi");
        sb.append("\n");
        return sb.toString();
    }

    //将MultipartFile类型转换成File类型
    private static File transferToFile(MultipartFile multipartFile){
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            System.out.println(filename.length);
            file = File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
