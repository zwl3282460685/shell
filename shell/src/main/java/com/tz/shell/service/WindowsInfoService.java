package com.tz.shell.service;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.entity.WindowsInfo;
import com.tz.shell.model.RespPageBean;
import com.tz.shell.repository.WindowsInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @date : 2021/3/4 18:09
 */
@Service
public class WindowsInfoService {

    @Autowired
    WindowsInfoDao windowsInfoDao;

    /**
     * 添加环境信息
     * @param windowsInfo
     * @return
     */
    public boolean addWindowsInfo(WindowsInfo windowsInfo) {
        windowsInfo.setCreateTime(new Date());
        windowsInfo.setUpdateTime(new Date());
        return null != windowsInfoDao.save(windowsInfo).getId();
    }

    /**
     * 查询全部并根据项目名称进行排序
     * @return
     */
    public List<WindowsInfo> findAllByOrderByProjectName() {
        return windowsInfoDao.findAllOrderByProjectName();
    }

    /**
     * 更新信息
     * @param environmentInfo
     * @return
     */
    public boolean updateInfo(WindowsInfo windowsInfo) {
        Date createTime = windowsInfoDao.findAllById(windowsInfo.getId()).getCreateTime();
        windowsInfo.setCreateTime(createTime);
        windowsInfo.setUpdateTime(new Date());
        return null != windowsInfoDao.save(windowsInfo).getId();
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @Transactional
    public int deleteInfoById(Long id) {
        return windowsInfoDao.deleteInfoById(id);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public RespPageBean getInfoByPage(Integer page, Integer size) {
        Page<WindowsInfo> data = windowsInfoDao.findAllByOrderByProjectName(PageRequest.of(page-1,size));
        Long total = windowsInfoDao.count();
        RespPageBean bean = new RespPageBean();
        bean.setTotal(total);
        bean.setData(data);
        return bean;
    }

    /**
     * 根据项目名称查询
     * @param keywords
     * @return
     */
    public List<WindowsInfo> findByProjectName(String keywords) {
        if(keywords != null){
            return windowsInfoDao.findByProjectNameLike("%" + keywords + "%");
        }
        return windowsInfoDao.findAllOrderByProjectName();
    }

    /**
     * 根据项目名称和环境类型查询
     * @param projectName
     * @param type
     * @return
     */
    public WindowsInfo findByNameAndType(String projectName, String type) {
        return windowsInfoDao.findByProjectNameAndEnvironmentType(projectName,type);
    }
}
