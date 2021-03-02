package com.tz.shell.service;

import com.tz.shell.repository.EnvironmentInfoDao;
import com.tz.shell.repository.JenkinsInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwl
 * @data 2021/2/27 21:28
 **/
@Service
public class ShellInfoService {
    @Autowired
    EnvironmentInfoDao environmentInfoDao;

    @Autowired
    JenkinsInfoDao jenkinsInfoDao;

    public List<String> getEnvProjectNameDistinct() {
        return environmentInfoDao.getAllProjectNameDistinct();
    }

    public List<String> getJenkinsInfoDistinct() {
        return jenkinsInfoDao.getAllProjectNameDistinct();
    }

    public List<String> getJenKinsTypeByName(String projectName) {
        return jenkinsInfoDao.getAllTypeByProjectName(projectName);
    }

    public List<String> getEnvTypeByName(String projectName) {
        return environmentInfoDao.getAllTypeByProjectName(projectName);
    }
}
