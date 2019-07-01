package com.demo.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.demo.common.AjaxResUtil;
import com.demo.common.AjaxResponse;
import com.demo.common.Page;

@Controller
public class SqlrunnerController {
    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "sqlrunner/{type}/{mapper}/{mapperId}")
    @ResponseBody
    public AjaxResponse select(@PathVariable("type") String type, @PathVariable("mapper") String mapper, @PathVariable("mapperId") String mapperId, Page page, HttpServletRequest httpServletRequest) {
        Map<String, Object> param = parseFill(httpServletRequest);
        try {
            Object obj;
            String s = String.format("com.dao.%s.%s", mapper, mapperId);
            if ("select".equals(type)) {
                if (page.getPage() == 0) {
                    obj = sqlSession.selectList(s, param);
                    return AjaxResUtil.createSuccess(obj);
                } else {
                    obj = sqlSession.selectList(s, param, page);
                    if (obj instanceof List) {
                        return AjaxResUtil.createSuccessRows((List) obj);
                    }
                }
            }
            if ("insert".equals(type)) {
                obj = sqlSession.insert(s, param);
                return AjaxResUtil.createSuccess(obj);
            }
            if ("delete".equals(type)) {
                obj = sqlSession.delete(s, param);
                return AjaxResUtil.createSuccess(obj);
            }
            if ("update".equals(type)) {
                obj = sqlSession.update(s, param);
                return AjaxResUtil.createSuccess(obj);
            }
            return AjaxResUtil.createError("sqlrunner type is error", "E1001");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return AjaxResUtil.createError(e.getMessage(), "E1000");
        }

    }

    public Map<String, Object> parseFill(HttpServletRequest httpServletRequest) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取所有请求参数，填充
        Map<String, String[]> p = httpServletRequest.getParameterMap();
        Iterator<String> it = p.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            String[] vs = p.get(key);
            if (vs.length == 1) {
                map.put(key, vs[0]);
            } else {
                map.put(key, vs);
            }
        }
        // 获取会话中的键值，填充
        HttpSession h = httpServletRequest.getSession();
        Enumeration<String> e = h.getAttributeNames();
        while (e.hasMoreElements()) {
            key = e.nextElement();
            map.put("session_" + key, h.getAttribute(key));
        }
        // 设置getServletPath地址,queryString
        map.put("request_ServletPath", httpServletRequest.getServletPath());
        map.put("request_QueryString", httpServletRequest.getQueryString());
        return map;
    }

}
