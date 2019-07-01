package com.demo.common;

import java.util.List;

public class AjaxResponse {
    private String code;
    private Object errorText;
    private Object data;
    private List rows;
    private Integer total;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Object getErrorText() {
        return errorText;
    }
    public void setErrorText(Object errorText) {
        this.errorText = errorText;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
