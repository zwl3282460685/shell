package com.tz.shell;

import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.repository.EnvironmentInfoDao;
import com.tz.shell.repository.JenkinsInfoDao;
import com.tz.shell.util.CreateBatlUtil;
import com.tz.shell.util.CreateShellUtil;
import com.tz.shell.util.TextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CreateShellApplicationTests {
//
//    @Autowired
//    CreateShellUtil createShellService;
//    @Autowired
//    EnvironmentInfoDao environmentInfoDao;
//
//    @Autowired
//    JenkinsInfoDao jenkinsInfoDao;
//
//    @Test
//    void contextLoads() {
//        String classpath= "/export/tomcat/webapps";
//        String jspath = "/export/tomcat/webapps/statics";
//        List<String> list = TextUtil.readText("C:\\Users\\tranzvision\\Desktop", "1.txt");
//        String DBString = createShellService.createDBShell(list, classpath, jspath);
//        System.out.println(DBString);
//        TextUtil.writeText("C:\\Users\\tranzvision\\Desktop", "db2.sh", DBString);
//    }
//
//    @Test
//    void test2(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date currentTime = new Date();
//        String time = simpleDateFormat.format(currentTime);
//        System.out.println(time);
//    }
//    @Test
//    void test3(){
//        String sourceClassPath = "/var/jenkins_home/workspace/哈工大/哈工大-教务portal-dev/target/university/WEB-INF/classes";
//        String sourceJsPath = "/var/jenkins_home/workspace/哈工大/哈工大-教务portal-dev/target/university/statics";
//        List<String> list = TextUtil.readText("C:\\Users\\tranzvision\\Desktop", "1.txt");
//        String getSbString = createShellService.getFileShell(list, sourceClassPath, sourceJsPath);
//        System.out.println(getSbString);
//        TextUtil.writeText("C:\\Users\\tranzvision\\Desktop", "get2.sh", getSbString);
//
//    }
//
//    @Test
//    void test4(){
//        String targetClassPath = "/export/tomcat/webapps";
//        String targetJsPath = "/export/tomcat/statics";
//        String transferPath = "/root/transfer";
//        List<String> list = TextUtil.readText("C:\\Users\\tranzvision\\Desktop", "1.txt");
//        String transferString = createShellService.createTransFerShell(list, targetClassPath, targetJsPath);
//        TextUtil.writeText("C:\\Users\\tranzvision\\Desktop", "transfer.sh", transferString);
//    }
//
//    @Test
//    void test5(){
//        String text = "1.txt";
//        String[] strings = text.split("\\.");
//        System.out.println(strings[0]);
//    }
//
    @Test
    void test6(){
        String sourceClassPath = "D:\\test2\\44" ;
        String sourceJsPath = "D:\\test\\33";
        List<String> list = TextUtil.readText("C:\\Users\\tranzvision\\Desktop", "1.txt");
        System.out.println(list);
        String transferString = CreateBatlUtil.createTransFerBat(list, sourceClassPath, sourceJsPath);
        System.out.println(transferString);
        TextUtil.writeText("C:\\Users\\tranzvision\\Desktop", "test.bat", transferString);
    }

    @Test
    void test7(){
        String targetClassPath = "D:\\test2\\44";
        String targetJsPath = "D:\\test\\33";
        List<String> list = TextUtil.readText("C:\\Users\\tranzvision\\Desktop", "1.txt");
        System.out.println(list);
        String DBString = CreateBatlUtil.createDBShell(list, targetClassPath, targetJsPath);
        System.out.println(DBString);
        TextUtil.writeText("C:\\Users\\tranzvision\\Desktop", "DB.bat", DBString);
    }
}
