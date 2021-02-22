package com.tz.shell.repository;

import com.tz.shell.entity.JenkinsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zwl
 * @date : 2021/2/22 10:02
 */
public interface JenkinsInfoDao extends JpaRepository<JenkinsInfo, Long> {
    JenkinsInfo findByProjectNameAndEnvironmentType(String projectName, String type);
}
