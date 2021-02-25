package com.tz.shell.repository;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.entity.JenkinsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/22 10:02
 */
public interface JenkinsInfoDao extends JpaRepository<JenkinsInfo, Long> {

    Page<JenkinsInfo> findAllByOrderByProjectName(Pageable page);

    @Query(value="select * from jenkins_info order by project_name", nativeQuery = true)
    List<JenkinsInfo> findAllOrderByProjectName();

    @Modifying
    @Query(value = "delete from jenkins_info where id = :id" ,nativeQuery = true)
    Integer deleteInfoById(@Param("id") Long id);

    JenkinsInfo findByProjectNameAndEnvironmentType(String projectName, String type);

    JenkinsInfo findAllById(Long id);

    List<JenkinsInfo> findByProjectNameLike(String projectName);
}
