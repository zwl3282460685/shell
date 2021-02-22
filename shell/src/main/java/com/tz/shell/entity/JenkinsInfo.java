package com.tz.shell.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zwl
 * @date : 2021/2/22 9:51
 */
@Entity
@Data
@Table(name = "jenkins_info")
public class JenkinsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name")
    private String projectName;    //项目名称
    @Column(name = "environment_type")
    private String environmentType;//环境类型
    @Column(name = "classes_path")
    private String classesPath;    //classes文件夹的位置
    @Column(name = "statics_path")
    private String staticsPath;    //statics文件夹的位置
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createTime;       //创建时间
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateTime;       //更新时间

}
