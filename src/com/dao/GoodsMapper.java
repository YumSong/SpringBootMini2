package com.dao;
import java.util.List;
import java.util.Map;
import com.demo.common.Page;

public interface GoodsMapper {
	Integer insert(Map goods);
	Integer delete(Map goods);
	Integer updateById(Map goods);
	List<Map> select(Map goods,Page page);
}
