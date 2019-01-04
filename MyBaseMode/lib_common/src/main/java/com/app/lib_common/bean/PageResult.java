package com.app.lib_common.bean;

/**
* 带分页信息
* @author lt
* @time 2018/12/11 11:40
*
**/
public class PageResult<T> {
    //总数
    private Integer total;
    //数量
    private Integer size;
    //总页数
    private Integer pages;
    //当前页数
    private Integer current;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }
}
