package com.minws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.JedisCluster;

import com.minws.common.ServiceResult;
import com.minws.model.User;
import com.minws.pagination.Condition;
import com.minws.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired(required = false)
	private JedisCluster jedisCluster;
	
	
	/**
	 * 根据id查询，返回数量，可能为null
	 * @param Long id
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult get(Long id){
		try{
			User user=super.selectByPrimaryKey(id);
			return ServiceResult.successWithData(user);
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 根据条件查询，返回数据列表
	 * @param Condition condition
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult query(Condition condition){
		try{
			List<User> users=super.selectByCondition(condition);
			return ServiceResult.successWithData(users);
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 根据条件查询，返回唯一数据或null
	 * @param Condition condition
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult queryOne(Condition condition){
		try{
			condition.setPage(1,1);
			List<User> users=super.selectByCondition(condition);
			return ServiceResult.successWithData(users.size()==0?null:users.get(0));
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 新增，返回是否成功
	 * @param User user
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult add(User user){
		try{
			int i=super.insert(user);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 批量新增，返回是否成功
	 * @param List<User> list
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult batchAdd(List<User> list){
		try{
			int i=super.batchInsert(list);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 根据id删除,返回是否成功
	 * @param Long id
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult delete(Long id){
		try{
			int i=super.deleteByPrimaryKey(id);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 根据id批量删除,返回是否成功
	 * @param List<User> list
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult batchDelete(List<User> list){
		try{
			int i=super.batchDeleteByPrimaryKey(list);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 更加主键更新非null的字段，返回成功与否
	 * @param user
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult updateSelectiveColumnByPrimaryKey(User user){
		try{
			int i=super.updateByPrimaryKeySelective(user);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
	 * 根据主键更新所有字段，返回成功与否
	 * @param user
	 * @return
	 */
	//@Transactional//事务
	public ServiceResult updateAllColumnByPrimaryKey(User user){
		try{
			int i=super.updateByPrimaryKey(user);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
	/**
     * 更加id批量更新,返回成功与否
     * 连接数据库要加 ：&allowMultiQueries=true 
     * @param List<User> list
     * @return
     */
	//@Transactional//事务
	public ServiceResult batchUpdateSelectiveColumnByPrimaryKey(List<User> list){
		try{
			int i=super.batchUpdateByPrimaryKeySelective(list);
			if(i>0)
				return ServiceResult.success();
			else
				return ServiceResult.fail();
		}catch(Exception e){
			return ServiceResult.exception(e);
		}
	}
}
