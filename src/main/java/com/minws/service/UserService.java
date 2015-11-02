package com.minws.service;

import java.util.List;

import com.minws.common.ServiceResult;
import com.minws.model.User;
import com.minws.pagination.Condition;

public interface UserService extends BaseService<User>{
	
	/**
	 * 根据id查询，返回数量，可能为null
	 * @param Long id
	 * @return
	 */
	public ServiceResult get(Long id);
	/**
	 * 根据条件查询，返回数据列表
	 * @param Condition condition
	 * @return
	 */
	public ServiceResult query(Condition condition);
	/**
	 * 根据条件查询，返回唯一数据或null
	 * @param Condition condition
	 * @return
	 */
	public ServiceResult queryOne(Condition condition);
	/**
	 * 新增，返回是否成功
	 * @param User user
	 * @return
	 */
	public ServiceResult add(User user);
	/**
	 * 批量新增，返回是否成功
	 * @param List<User> list
	 * @return
	 */
	public ServiceResult batchAdd(List<User> list);
	/**
	 * 根据id删除,返回是否成功
	 * @param Long id
	 * @return
	 */
	public ServiceResult delete(Long id);
	/**
	 * 根据id批量删除,返回是否成功
	 * @param List<User> list
	 * @return
	 */
	public ServiceResult batchDelete(List<User> list);
	/**
	 * 更加主键更新非null的字段，返回成功与否
	 * @param user
	 * @return
	 */
	public ServiceResult updateSelectiveColumnByPrimaryKey(User user);
	/**
	 * 根据主键更新所有字段，返回成功与否
	 * @param user
	 * @return
	 */
	public ServiceResult updateAllColumnByPrimaryKey(User user);
	/**
     * 更加id批量更新,返回成功与否
     * 连接数据库要加 ：&allowMultiQueries=true 
     * @param List<User> list
     * @return
     */
	public ServiceResult batchUpdateSelectiveColumnByPrimaryKey(List<User> list);
	
}
