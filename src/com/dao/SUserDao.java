package com.dao;

import java.util.List;
import java.util.Map;

import com.demo.common.Page;


public interface SUserDao {
    void insert(Map sUser);

    void update(Map sUser);
    
    void delete(Map sUser);

    List<Map> select(Map sUser, Page page);

    List<Map> selectAll();

    Map selectByUsername(Map sUser);
}
