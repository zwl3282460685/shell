package com.tz.shell.controller.windows;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.entity.WindowsInfo;
import com.tz.shell.service.BatInfoService;
import com.tz.shell.service.EnvironmentInfoService;
import com.tz.shell.service.JenKinsInfoService;
import com.tz.shell.service.WindowsInfoService;
import com.tz.shell.util.CreateBatlUtil;
import com.tz.shell.util.CreateShellUtil;
import com.tz.shell.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/20 9:09
 */
@RestController
@RequestMapping("/bat")
public class BatController {

    @Autowired
    WindowsInfoService windowsInfoService;

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
        WindowsInfo windowsInfo = windowsInfoService.findByNameAndType(projectName, type);
        String targetClassPath = windowsInfo.getClassesPath();
        String targetJSPath = windowsInfo.getJsPath();
        String data = CreateBatlUtil.createDBShell(list, targetClassPath, targetJSPath);
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
        WindowsInfo windowsInfo = windowsInfoService.findByNameAndType(projectName, type);
        String targetClassPath = windowsInfo.getClassesPath();
        String targetJSPath = windowsInfo.getJsPath();
        String data = CreateBatlUtil.createTransFerBat(list, targetClassPath, targetJSPath);
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
                                                      @RequestParam(value = "type") String type) throws IOException {
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
        WindowsInfo windowsInfo = windowsInfoService.findByNameAndType(projectName, type);
        String sourceClassPath = windowsInfo.getClassesPath();
        String sourceJsPath = windowsInfo.getJsPath();
        String data = CreateShellUtil.getFileShell(list, sourceClassPath, sourceJsPath);
        return TextUtil.writeText2(data);
    }
}
