package com.tz.shell.controller.linux;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.model.RespBean;
import com.tz.shell.service.ShellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zwl
 * @data 2021/2/27 21:23
 **/
@RestController
@RequestMapping("/shellInfo")
public class ShellInfoController {

    @Autowired
    ShellInfoService shellInfoService;

    @GetMapping("/env")
    public List<String> getEnvProjectNameDistinct(){
        return shellInfoService.getEnvProjectNameDistinct();
    }

    @GetMapping("/jenkins")
    public List<String> getJenkinsProjectNameDistinct(){
        return shellInfoService.getJenkinsInfoDistinct();
    }

    @GetMapping("/JenkinsType")
    public List<String> getJenKinsTypeByName(@RequestParam("projectName") String projectName){
        return shellInfoService.getJenKinsTypeByName(projectName);
    }

    @GetMapping("/envType")
    public List<String> getEnvTypeByName(@RequestParam("projectName") String projectName){
        return shellInfoService.getEnvTypeByName(projectName);
    }
}
