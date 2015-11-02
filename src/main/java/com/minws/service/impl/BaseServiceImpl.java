package com.minws.service.impl;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.minws.dao.BaseDao;
import com.minws.pagination.Condition;
import com.minws.service.BaseService;
import com.minws.util.JedisUtils;

public class BaseServiceImpl<T> implements BaseService<T>{
	
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired(required = false)
	private JedisUtils jedisUtils;
	
	@Autowired
	private BaseDao<T> baseDao;
	
	private Class<T> entityClass = null;
	
	public BaseServiceImpl() {
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<T>)p[0];
        }
	}

	/**
	 * 根据主键id查询，返回对象或null
	 * @param id
	 * @return
	 */
	public T selectByPrimaryKey(Long id){
		//读缓存
		if(entityClass!=null && jedisUtils!=null){
			Object obj=jedisUtils.hgetAll(entityClass.getSimpleName()+":"+id, entityClass);
			if(obj!=null){
				return (T) obj;
			}
		}
		//读数据库
		T t= baseDao.selectByPrimaryKey(id);
		if(t!=null){
			//放入缓存
			jedisUtils.hmset(t.getClass().getSimpleName()+":"+id, t);
		}
		return t;
	}  
	/**
	 * 根据条件查询，没有数据返回空数组
	 * @param condition
	 * @return
	 */
	public List<T> selectByCondition(Condition condition){
		return baseDao.selectByCondition(condition);
	}
	/**
	 * 根据条件查询一条数据，没有数据返回空
	 * @param condition
	 * @return
	 */
	public T selectOneByCondition(Condition condition){
		condition.setPage(1,1);
		List<T> list= baseDao.selectByCondition(condition);
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 插入，成功返回1，否则抛异常
	 * @param record
	 * @return
	 */
	public int insert(T record){
		int i= baseDao.insert(record);
		if(i>0 && jedisUtils!=null){
			try{
				Field field=record.getClass().getDeclaredField("id");
				if(field!=null){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), record.getClass());
					Method rM = pd.getReadMethod();//获得读方法
					Long id = (Long) rM.invoke(record);
					Boolean flag=jedisUtils.hmset(record.getClass().getSimpleName()+":"+id, record);
					if(flag)
						jedisUtils.expire(record.getClass().getSimpleName()+":"+id, 24*60*60);//1天
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.info("radis存储失败");
			}
		}
		return i;
	}
	/**
	 * 批量插入，返回插入条数，即返回list.size()
	 * @param list
	 * @return
	 */
	public int batchInsert(List<T> list){
		int i= baseDao.batchInsert(list);
		if(i==list.size() && jedisUtils!=null){
			try{
				//存redis
				for(int j=0;j<list.size();j++){
					Field field=list.get(j).getClass().getDeclaredField("id");
					if(field!=null){
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), list.get(j).getClass());
						Method rM = pd.getReadMethod();//获得读方法
						Long id = (Long) rM.invoke(list.get(j));
						Boolean flag=jedisUtils.hmset(list.get(j).getClass().getSimpleName()+":"+id, list.get(j));
						if(flag)
							jedisUtils.expire(list.get(j).getClass().getSimpleName()+":"+id, 24*60*60);//1天
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.info("radis存储失败");
			}
		}
		return i;
	}
	/**
	 * 根据主键删除，返回成功删除条数，这里1个主键，则删除成功返回1
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Long id){
		int i= baseDao.deleteByPrimaryKey(id);
		if(i>0 && jedisUtils!=null){
			jedisUtils.del(entityClass.getSimpleName()+":"+id);
		}
		return i;
	}
	/**
	 * 通过主键批量删除，返回成功删除条数
	 * @param list
	 * @return
	 */
	public int batchDeleteByPrimaryKey(List<T> list){
		int i= baseDao.batchDeleteByPrimaryKey(list);
		if(i==list.size() && jedisUtils!=null){
			try{
				//存redis
				for(int j=0;j<list.size();j++){
					Field field=list.get(j).getClass().getDeclaredField("id");
					if(field!=null){
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), list.get(j).getClass());
						Method rM = pd.getReadMethod();//获得读方法
						Long id = (Long) rM.invoke(list.get(j));
						jedisUtils.del(entityClass.getSimpleName()+":"+id);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.info("radis存储失败");
			}
		}
		return i;
	}
	/**
	 * 更加主键更新非null的字段，返回更新条数，一个主键就是1了
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(T record){
		int i= baseDao.updateByPrimaryKeySelective(record);
		if(i>0 && jedisUtils!=null){
			try{
				Field field=record.getClass().getDeclaredField("id");
				if(field!=null){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), record.getClass());
					Method rM = pd.getReadMethod();//获得读方法
					Long id = (Long) rM.invoke(record);
					if(jedisUtils.exists(record.getClass().getSimpleName()+":"+id)){
						jedisUtils.hmset(record.getClass().getSimpleName()+":"+id, record);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.info("radis存储失败");
			}
		}
		return i;
	}
	/**
	 * 根据主键更新所有字段，返回更新条数，一个主键就是1了
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(T record){
		int i= baseDao.updateByPrimaryKey(record);
		if(i>0 && jedisUtils!=null){
			try{
				Field field=record.getClass().getDeclaredField("id");
				if(field!=null){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), record.getClass());
					Method rM = pd.getReadMethod();//获得读方法
					Long id = (Long) rM.invoke(record);
					if(jedisUtils.exists(record.getClass().getSimpleName()+":"+id)){
						jedisUtils.hmset(record.getClass().getSimpleName()+":"+id, record);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.info("radis存储失败");
			}
		}
		return i;
	}
	/**
     * 更加id批量更新,返回成功与否
     * 连接数据库要加 ：&allowMultiQueries=true 
     * @param list
     * @return
     */
	public int batchUpdateByPrimaryKeySelective(List<T> list){
		int i= baseDao.batchUpdateByPrimaryKeySelective(list);
		if(i==list.size() && jedisUtils!=null){
			for(int j=0;j<list.size();j++){
				T record=list.get(j);
				try{
					Field field=record.getClass().getDeclaredField("id");
					if(field!=null){
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), record.getClass());
						Method rM = pd.getReadMethod();//获得读方法
						Long id = (Long) rM.invoke(record);
						if(jedisUtils.exists(record.getClass().getSimpleName()+":"+id)){
							jedisUtils.hmset(record.getClass().getSimpleName()+":"+id, record);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					logger.info("radis存储失败");
				}
			}
		}
		return i;
	}

}
