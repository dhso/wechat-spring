package com.minws.service;

import java.util.List;

//import org.apache.ibatis.annotations.Param;


import com.minws.pagination.Condition;

public abstract interface BaseService<T>  {

	T selectByPrimaryKey(Long id);    
    List<T> selectByCondition(Condition condition);//@Param("condition")
	
	int insert(T record);
	int batchInsert(List<T> list);
	
	int deleteByPrimaryKey(Long id);
	int batchDeleteByPrimaryKey(List<T> list);
	
	int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
    int batchUpdateByPrimaryKeySelective(List<T> list); 	
}
