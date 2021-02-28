package com.tz.shell.controller.linux;

import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.model.RespBean;
import com.tz.shell.model.RespPageBean;
import com.tz.shell.service.JenKinsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/23 15:06
 */
@RestController
@RequestMapping("/jenkins")
public class JenkinsInfoController {
    @Autowired
    JenKinsInfoService jenKinsInfoService;

    @PostMapping("/")
    public RespBean addJenkinsInfo(@RequestBody JenkinsInfo jenkinsInfo){
        if(jenKinsInfoService.addJenKinsInfo(jenkinsInfo)){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @GetMapping("/findAll")
    public List<JenkinsInfo> findAll(){
        return jenKinsInfoService.findAllByOrderByProjectName();
    }

    @PutMapping("/")
    public RespBean updateInfo(@RequestBody JenkinsInfo jenkinsInfo){
        if(jenKinsInfoService.updateInfo(jenkinsInfo)){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteInfoById(@PathVariable Long id){
        if(jenKinsInfoService.deleteInfoById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败！");
    }

    @GetMapping("/")
    public RespPageBean getInfoByPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer size){
        return jenKinsInfoService.getInfoByPage(page, size);
    }

    @GetMapping("/findByName")
    public List<JenkinsInfo> findAll(@RequestParam("keywords") String keywords){
        return jenKinsInfoService.findByProjectName(keywords);
    }
}
