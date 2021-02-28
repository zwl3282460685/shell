package com.tz.shell.repository;

import com.tz.shell.entity.EnvironmentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/19 14:51
 */
public interface EnvironmentInfoDao extends JpaRepository<EnvironmentInfo, Long>{

    @Query(value="select distinct project_name from environment_info order by project_name", nativeQuery= true)
    List<String>  getAllProjectNameDistinct();

    @Query(value="select * from environment_info order by project_name", nativeQuery = true)
    List<EnvironmentInfo> findAllOrderByProjectName();

    @Modifying
    @Query(value = "delete from environment_info where id = :id" ,nativeQuery = true)
    Integer deleteInfoById(@Param("id") Long id);

    @Query(value="select environment_type from environment_info where project_name= :projectName", nativeQuery = true)
    List<String> getAllTypeByProjectName(String projectName);

    EnvironmentInfo findByProjectNameAndEnvironmentType(String projectName, String type);
    EnvironmentInfo findAllById(Long id);
    List<EnvironmentInfo> findByProjectNameLike(String projectName);
    Page<EnvironmentInfo> findAllByOrderByProjectName(Pageable page);

}
