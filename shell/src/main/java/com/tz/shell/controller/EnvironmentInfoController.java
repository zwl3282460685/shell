package com.tz.shell.controller;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.model.RespBean;
import com.tz.shell.model.RespPageBean;
import com.tz.shell.service.EnvironmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/18 10:20
 */
@RestController
@RequestMapping("/info")
public class EnvironmentInfoController {

    @Autowired
    EnvironmentInfoService environmentInfoService;

    @PostMapping("/")
    public RespBean addEnvironmentInfo(@RequestBody EnvironmentInfo environmentInfo){
        if(environmentInfoService.addEnvironmentInfo(environmentInfo)){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @GetMapping("/findAll")
    public List<EnvironmentInfo> findAll(){
        return environmentInfoService.findAllByOrderByProjectName();
    }

    @PutMapping("/")
    public RespBean updateInfo(@RequestBody EnvironmentInfo environmentInfo){
        if(environmentInfoService.updateInfo(environmentInfo)){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteInfoById(@PathVariable Long id){
        if(environmentInfoService.deleteInfoById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败！");
    }

    @GetMapping("/")
    public RespPageBean getInfoByPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer size){
        return environmentInfoService.getInfoByPage(page, size);
    }

    @GetMapping("/findByName")
    public List<EnvironmentInfo> findAll(@RequestParam("keywords") String keywords){
        return environmentInfoService.findByProjectName(keywords);
    }
}
