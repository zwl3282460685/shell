package com.tz.shell.repository;

import com.tz.shell.entity.EnvironmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author zwl
 * @date : 2021/2/19 14:51
 */
public interface EnvironmentInfoDao extends JpaRepository<EnvironmentInfo, Long>{
    List<EnvironmentInfo> findAllByOrderByProjectName();

    @Modifying
    @Query(value = "delete from environment_info where id = :id" ,nativeQuery = true)
    Integer deleteInfoById(@Param("id") Long id);

    EnvironmentInfo findByProjectNameAndEnvironmentType(String projectName, String type);
    EnvironmentInfo findAllById(Long id);

    List<EnvironmentInfo> findByProjectNameLike(String projectName);
}
