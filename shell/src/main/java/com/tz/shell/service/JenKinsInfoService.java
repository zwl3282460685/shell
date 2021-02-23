package com.tz.shell.service;

import com.tz.shell.entity.JenkinsInfo;
import com.tz.shell.repository.JenkinsInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param JenkinsInfo
     * @return
     */
    public boolean addJenKinsInfo(JenkinsInfo jenkinsInfo){
        return null != jenkinsInfoDao.save(jenkinsInfo).getId();
    }

    public JenkinsInfo findByNameAndType(String projectName, String type){
        return jenkinsInfoDao.findByProjectNameAndEnvironmentType(projectName, type);
    }

}
