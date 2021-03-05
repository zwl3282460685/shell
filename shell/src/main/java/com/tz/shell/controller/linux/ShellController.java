package com.tz.shell.controller.linux;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.service.EnvironmentInfoService;
import com.tz.shell.service.JenKinsInfoService;
import com.tz.shell.util.CreateShellUtil;
import com.tz.shell.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/20 9:09
 */
@RestController
@RequestMapping("/shell")
public class ShellController {

    @Autowired
    EnvironmentInfoService environmentInfoService;

    @Autowired
    JenKinsInfoService jenKinsInfoService;

    /**
     * 创建备份脚本
     * @param file
     * @param projectName
     * @param type
     * @return
     */
    @PostMapping("/createDB")
    public ResponseEntity<byte[]> createDB( MultipartFile file,
                                            @RequestParam(value = "projectName") String projectName,
                                            @RequestParam(value = "type") String type) {
        List<String> list = TextUtil.readText2(file);
        EnvironmentInfo environmentInfo = environmentInfoService.findByNameAndType(projectName, type);
        String targetClassPath = environmentInfo.getClassesPath();
        String targetJSPath = environmentInfo.getJsPath();
        String data = CreateShellUtil.createDBShell(list, targetClassPath, targetJSPath);
        return TextUtil.writeText2(data);
    }

    /**
     * 创建迁移脚本
     * @param file
     * @param projectName
     * @param type
     * @return
     */
    @PostMapping("/createTransFerShell")
    public ResponseEntity<byte[]> createTransferShell(MultipartFile file,
                                                      @RequestParam(value = "projectName") String projectName,
                                                      @RequestParam(value = "type") String type) {
        List<String> list = TextUtil.readText2(file);
        EnvironmentInfo environmentInfo = environmentInfoService.findByNameAndType(projectName, type);
        String targetClassPath = environmentInfo.getClassesPath();
        String targetJSPath = environmentInfo.getJsPath();
        String data = CreateShellUtil.createTransFerShell(list, targetClassPath, targetJSPath);
        return TextUtil.writeText2(data);
    }

    /**
     * 创建从jenkins获取迁移文件的脚本
     * @param file
     * @param projectName
     * @param type
     * @return
     */
    @PostMapping("/getFilesFromJenkins")
    public ResponseEntity<byte[]> getFileShellFromJenkins(MultipartFile file,
                                                      @RequestParam(value = "projectName") String projectName,
                                                      @RequestParam(value = "type") String type) {
        List<String> list = TextUtil.readText2(file);
        System.out.println(list);
        JenkinsInfo jenkinsInfo = jenKinsInfoService.findByNameAndType(projectName, type);
        String sourceClassPath = jenkinsInfo.getClassesPath();
        String sourceJsPath = jenkinsInfo.getStaticsPath();
        String data = CreateShellUtil.getFileShell(list, sourceClassPath, sourceJsPath);
        return TextUtil.writeText2(data);
    }

    /**
     * 创建从项目中获取迁移文件的脚本
     * @param file
     * @param projectName
     * @param type
     * @return
     */
    @PostMapping("/getFilesFromProject")
    public ResponseEntity<byte[]> getFileShellFromProject(MultipartFile file,
                                                          @RequestParam(value = "projectName") String projectName,
                                                          @RequestParam(value = "type") String type){
        List<String> list = TextUtil.readText2(file);
        EnvironmentInfo environmentInfo = environmentInfoService.findByNameAndType(projectName, type);
        String sourceClassPath = environmentInfo.getClassesPath();
        String sourceJsPath = environmentInfo.getJsPath();
        String data = CreateShellUtil.getFileShell(list, sourceClassPath, sourceJsPath);
        return TextUtil.writeText2(data);

    }
}
