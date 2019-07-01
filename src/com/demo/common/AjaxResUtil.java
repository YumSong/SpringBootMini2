package com.demo.common;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

public class AjaxResUtil {
    public static AjaxResponse createSuccess() {
        AjaxResponse a = new AjaxResponse();
        a.setCode("S1000");
        return a;
    }
    
    public static AjaxResponse createSuccess(Object data) {
        AjaxResponse a = new AjaxResponse();
        a.setCode("S1000");
        a.setData(data);
        return a;
    }

    public static AjaxResponse createError(Object errorText, String code) {
        AjaxResponse a = new AjaxResponse();
        a.setCode(code);
        a.setErrorText(errorText);
        return a;
    }

    public static AjaxResponse createSuccessRows(List rows) {
        AjaxResponse a = new AjaxResponse();
        a.setCode("S1000");
        a.setRows(rows);
        a.setTotal(Page.getCount(rows));
        return a;
    }
}
