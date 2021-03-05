package com.tz.shell.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * window环境信息
 * @author zwl
 * @date : 2021/3/4 17:49
 */
@Entity
@Data
@Table(name="windows_info")
public class WindowsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "project_name")
    private String projectName;     //项目名称
    @Column(name= "environment_type")
    private String environmentType; //环境类型
    @Column(name = "classes_path")
    private String classesPath;     //classes文件夹的类型
    @Column(name = "js_path")
    private String jsPath;          //statics文件夹的类型
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createTime;        //创建时间
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateTime;        //更新时间
}
