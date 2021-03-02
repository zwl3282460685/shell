package com.tz.shell.service;

import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.model.RespPageBean;
import com.tz.shell.repository.JenkinsInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/22 10:04
 */
@Service
public class JenKinsInfoService {

    @Autowired
    JenkinsInfoDao jenkinsInfoDao;

    /**
     * 添加环境信息
     * @param jenkinsInfo
     * @return
     */
    public boolean addJenKinsInfo(JenkinsInfo jenkinsInfo){
        jenkinsInfo.setCreateTime(new Date());
        jenkinsInfo.setUpdateTime(new Date());
        return null != jenkinsInfoDao.save(jenkinsInfo).getId();
    }

    /**
     * 根据项目名称和环境类型查询
     * @param projectName
     * @param type
     * @return
     */
    public JenkinsInfo findByNameAndType(String projectName, String type){
        return jenkinsInfoDao.findByProjectNameAndEnvironmentType(projectName, type);
    }

    /**
     * 查询全部并根据项目名称进行排序
     * @return
     */
    public List<JenkinsInfo> findAllByOrderByProjectName() {
        return jenkinsInfoDao.findAllOrderByProjectName();
    }

    /**
     * 更新信息
     * @param jenkinsInfo
     * @return
     */
    public Boolean updateInfo(JenkinsInfo jenkinsInfo) {
        Date createTime = jenkinsInfoDao.findAllById(jenkinsInfo.getId()).getCreateTime();
        jenkinsInfo.setCreateTime(createTime);
        jenkinsInfo.setUpdateTime(new Date());
        return null != jenkinsInfoDao.save(jenkinsInfo).getId();
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @Transactional
    public Integer deleteInfoById(Long id) {
        return jenkinsInfoDao.deleteInfoById(id);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public RespPageBean getInfoByPage(Integer page, Integer size){
        Page<JenkinsInfo> data = jenkinsInfoDao.findAllByOrderByProjectName(PageRequest.of(page -1, size));
        Long total = jenkinsInfoDao.count();
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
    public List<JenkinsInfo> findByProjectName(String keywords) {
        System.out.println(keywords);
        if(keywords != null){
            return jenkinsInfoDao.findByProjectNameLike("%" + keywords + "%");
        }
        return jenkinsInfoDao.findAllOrderByProjectName();
    }
}
