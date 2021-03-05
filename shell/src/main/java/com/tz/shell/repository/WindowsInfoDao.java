package com.tz.shell.repository;

import com.tz.shell.entity.WindowsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zwl
 * @date : 2021/3/4 17:53
 */
public interface WindowsInfoDao extends JpaRepository<WindowsInfo, Long> {
    @Query(value="select distinct project_name from windows_info order by project_name", nativeQuery= true)
    List<String> getAllProjectNameDistinct();

    @Query(value="select * from windows_info order by project_name", nativeQuery = true)
    List<WindowsInfo> findAllOrderByProjectName();

    @Modifying
    @Query(value = "delete from windows_info where id = :id" ,nativeQuery = true)
    Integer deleteInfoById(@Param("id") Long id);

    @Query(value="select environment_type from windows_info where project_name= :projectName", nativeQuery = true)
    List<String> getAllTypeByProjectName(String projectName);

    WindowsInfo findByProjectNameAndEnvironmentType(String projectName, String type);
    WindowsInfo findAllById(Long id);
    List<WindowsInfo> findByProjectNameLike(String projectName);
    Page<WindowsInfo> findAllByOrderByProjectName(Pageable page);
}
