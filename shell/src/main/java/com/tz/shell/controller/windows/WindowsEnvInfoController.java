package com.tz.shell.controller.windows;

import com.tz.shell.entity.WindowsInfo;
import com.tz.shell.model.RespBean;
import com.tz.shell.model.RespPageBean;
import com.tz.shell.service.WindowsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zwl
 * @date : 2021/3/4 18:01
 */
@RestController
@RequestMapping("/winInfo")
public class WindowsEnvInfoController {
    @Autowired
    WindowsInfoService windowsInfoService;

    @PostMapping("/")
    public RespBean addEnvironmentInfo(@RequestBody WindowsInfo windowsInfo){
        if(windowsInfoService.addWindowsInfo(windowsInfo)){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @GetMapping("/findAll")
    public List<WindowsInfo> findAll(){
        return windowsInfoService.findAllByOrderByProjectName();
    }

    @PutMapping("/")
    public RespBean updateInfo(@RequestBody WindowsInfo windowsInfo){
        if(windowsInfoService.updateInfo(windowsInfo)){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteInfoById(@PathVariable Long id){
        if(windowsInfoService.deleteInfoById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败！");
    }

    @GetMapping("/")
    public RespPageBean getInfoByPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer size){
        return windowsInfoService.getInfoByPage(page, size);
    }

    @GetMapping("/findByName")
    public List<WindowsInfo> findAll(@RequestParam("keywords") String keywords){
        return windowsInfoService.findByProjectName(keywords);
    }
}
