package com.tz.shell.service;

import com.tz.shell.repository.JenkinsInfoDao;
import com.tz.shell.repository.WindowsInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwl
 * @data 2021/2/27 21:28
 **/
@Service
public class BatInfoService {
    @Autowired
    WindowsInfoDao windowsInfoDao;

    @Autowired
    JenkinsInfoDao jenkinsInfoDao;

    public List<String> getEnvProjectNameDistinct() {
        return windowsInfoDao.getAllProjectNameDistinct();
    }

    public List<String> getJenkinsInfoDistinct() {
        return jenkinsInfoDao.getAllProjectNameDistinct();
    }

    public List<String> getJenKinsTypeByName(String projectName) {
        return jenkinsInfoDao.getAllTypeByProjectName(projectName);
    }

    public List<String> getEnvTypeByName(String projectName) {
        return windowsInfoDao.getAllTypeByProjectName(projectName);
    }
}
