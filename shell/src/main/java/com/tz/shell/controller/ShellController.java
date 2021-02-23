package com.tz.shell.controller;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.service.EnvironmentInfoService;
import com.tz.shell.service.JenKinsInfoService;
import com.tz.shell.util.CreateShellUtil;
import com.tz.shell.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/createDB")
    public ResponseEntity<byte[]> createDB(@RequestParam(value = "file") MultipartFile file,
                                             @RequestParam(value = "projectName") String projectName,
                                             @RequestParam(value = "type") String type){
        List<String> list = TextUtil.readText2(file);
        EnvironmentInfo environmentInfo = environmentInfoService.findByNameAndType(projectName, type);
        String targetClassPath = environmentInfo.getClassesPath();
        String targetJSPath = environmentInfo.getJsPath();
        String data = CreateShellUtil.createDBShell(list, targetClassPath, targetJSPath);
        return TextUtil.writeText2(data);
    }

    @PostMapping("/createTransFerShell")
    public ResponseEntity<byte[]> createTransferShell(@RequestParam(value = "file") MultipartFile file,
                                                      @RequestParam(value = "projectName") String projectName,
                                                      @RequestParam(value = "type") String type,
                                                      @RequestParam(value = "transferPath") String transferPath){
        List<String> list = TextUtil.readText2(file);
        EnvironmentInfo environmentInfo = environmentInfoService.findByNameAndType(projectName, type);
        String targetClassPath = environmentInfo.getClassesPath();
        String targetJSPath = environmentInfo.getJsPath();
        String data = CreateShellUtil.createTransFerShell(list, targetClassPath, targetJSPath, transferPath);
        return TextUtil.writeText2(data);
    }

    @PostMapping("/getFileShell")
    public ResponseEntity<byte[]> getFileShell(@RequestParam(value = "file") MultipartFile file,
                                                      @RequestParam(value = "projectName") String projectName,
                                                      @RequestParam(value = "type") String type){
        List<String> list = TextUtil.readText2(file);
        JenkinsInfo jenkinsInfo = jenKinsInfoService.findByNameAndType(projectName, type);
        String sourceClassPath = jenkinsInfo.getClassesPath();
        String sourceJsPath = jenkinsInfo.getStaticsPath();
        String data = CreateShellUtil.getFileShell(list, sourceClassPath, sourceJsPath);
        return TextUtil.writeText2(data);
    }
}
