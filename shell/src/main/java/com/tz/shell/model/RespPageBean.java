package com.tz.shell.model;

import org.springframework.data.domain.Page;

/**
 *
 * @author zwl
 * @date : 2021/2/19 15:56
 */
public class RespPageBean {
    private Long total;
    private Page<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Page<?> getData() {
        return data;
    }

    public void setData(Page<?> data) {
        this.data = data;
    }
}
