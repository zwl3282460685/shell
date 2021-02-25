package com.tz.shell.service;

import com.tz.shell.entity.EnvironmentInfo;
import com.tz.shell.model.RespPageBean;
import com.tz.shell.repository.EnvironmentInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @date : 2021/2/19 16:16
 */
@Service
public class EnvironmentInfoService {
    @Autowired
    EnvironmentInfoDao environmentInfoDao;

    /**
     * 根据项目名称和环境类型查询
     * @param projectName
     * @param type
     * @return
     */
    public EnvironmentInfo findByNameAndType(String projectName, String type){
        return environmentInfoDao.findByProjectNameAndEnvironmentType(projectName, type);
    }

    /**
     * 添加环境信息
     * @param environmentInfo
     * @return
     */
    public boolean addEnvironmentInfo(EnvironmentInfo environmentInfo) {
        environmentInfo.setCreateTime(new Date());
        environmentInfo.setUpdateTime(new Date());
        return null != environmentInfoDao.save(environmentInfo).getId();
    }

    /**
     * 查询全部并根据项目名称进行排序
     * @return
     */
    public List<EnvironmentInfo> findAllByOrderByProjectName() {
        return environmentInfoDao.findAllOrderByProjectName();
    }

    /**
     * 更新信息
     * @param environmentInfo
     * @return
     */
    public Boolean updateInfo(EnvironmentInfo environmentInfo) {
        Date createTime = environmentInfoDao.findAllById(environmentInfo.getId()).getCreateTime();
        environmentInfo.setCreateTime(createTime);
        environmentInfo.setUpdateTime(new Date());
        return null != environmentInfoDao.save(environmentInfo).getId();
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @Transactional
    public Integer deleteInfoById(Long id) {
        return environmentInfoDao.deleteInfoById(id);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public RespPageBean getInfoByPage(Integer page, Integer size){
        Page<EnvironmentInfo> data = environmentInfoDao.findAllByOrderByProjectName(PageRequest.of(page -1, size));
        Long total = environmentInfoDao.count();
        RespPageBean bean = new RespPageBean();
        bean.setTotal(total);
        bean.setData(data);
        return bean;
    }

    /**
     * 根据项目名称查询
     * @param keywords
     * @return
     */
    public List<EnvironmentInfo> findByProjectName(String keywords) {
        System.out.println(keywords);
        if(keywords != null){
            return environmentInfoDao.findByProjectNameLike("%" + keywords + "%");
        }
        return environmentInfoDao.findAllOrderByProjectName();
    }
}
