package com.tz.shell.controller.windows;

import com.tz.shell.service.BatInfoService;
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
@RequestMapping("/batInfo")
public class BatInfoController {

    @Autowired
    BatInfoService batInfoService;

    @GetMapping("/env")
    public List<String> getEnvProjectNameDistinct(){
        return batInfoService.getEnvProjectNameDistinct();
    }

    @GetMapping("/jenkins")
    public List<String> getJenkinsProjectNameDistinct(){
        return batInfoService.getJenkinsInfoDistinct();
    }

    @GetMapping("/JenkinsType")
    public List<String> getJenKinsTypeByName(@RequestParam("projectName") String projectName){
        return batInfoService.getJenKinsTypeByName(projectName);
    }

    @GetMapping("/envType")
    public List<String> getEnvTypeByName(@RequestParam("projectName") String projectName){
        return batInfoService.getEnvTypeByName(projectName);
    }
}
