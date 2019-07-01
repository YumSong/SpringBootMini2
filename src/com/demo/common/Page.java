package com.demo.common;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public class Page extends PageBounds {
    public Page() {
        // 指定默认值，保证 containsTotalCount初始为true
        super(1, 20);
    }

    private List result;
    private Integer total;

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public void setTotal(List rows) {
        this.total = getCount(rows);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static Integer getCount(List rows) {
        try {
            PageList pageList = (PageList) rows;
            return pageList.getPaginator().getTotalCount();
        } catch (Exception e) {
            System.out.println("set total is error :" + e.getMessage());
            return 0;
        }
    }
}
