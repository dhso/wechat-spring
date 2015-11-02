package com.minws.dao;

import java.util.List;

//import org.apache.ibatis.annotations.Param;


import com.minws.pagination.Condition;



public interface BaseDao<T> {
	
	/**
	 * 根据主键id查询，返回对象或null
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(Long id);    
	/**
	 * 根据条件查询，没有数据返回空数组
	 * @param condition
	 * @return
	 */
    List<T> selectByCondition(Condition condition);//@Param("condition")
    
	/**
	 * 插入，成功返回1，否则抛异常
	 * @param record
	 * @return
	 */
	int insert(T record);
	/**
	 * 批量插入，返回插入条数，即返回list.size()
	 * @param list
	 * @return
	 */
	int batchInsert(List<T> list);
	
	/**
	 * 根据主键删除，返回成功删除条数，这里1个主键，则删除成功返回1
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 通过主键批量删除，返回成功删除条数
	 * @param list
	 * @return
	 */
	int batchDeleteByPrimaryKey(List<T> list);
	/**
	 * 更加主键更新非null的字段，返回更新条数，一个主键就是1了
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);
	/**
	 * 根据主键更新所有字段，返回更新条数，一个主键就是1了
	 * @param record
	 * @return
	 */
    int updateByPrimaryKey(T record);
    /**
     * 更加id批量更新,返回成功与否
     * 连接数据库要加 ：&allowMultiQueries=true 
     * @param list
     * @return
     */
    int batchUpdateByPrimaryKeySelective(List<T> list);    
    
}