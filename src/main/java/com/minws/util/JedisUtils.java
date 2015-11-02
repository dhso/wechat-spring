package com.minws.util;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtils {
	
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	private JedisPool jedisPool;
	
	private JedisUtils(){}

	public JedisUtils(JedisPool jedisPool){
		this.jedisPool=jedisPool;
	}
	/**
	 * 删除给定的一个或多个 key
	 * 返回 删除的 key 的数量
	 */
	public Long del(String ... keys){
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			return jedis.del(keys);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			if(jedis!=null)
				jedis.close();
		}
	}
	/**
	 * 检查给定 key 是否存在
	 * @param key
	 * @return
	 */
	public Boolean exists(String key){
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			return jedis.exists(key);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			if(jedis!=null)
				jedis.close();
		}
	}
	/**
	 * 保存对象
	 * @param key
	 * @param obj
	 * @return
	 */
	public Boolean hmset(String key,Object obj){
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			String isOk=jedis.hmset(key,Util.beanToMap(obj));
			if("OK".equals(isOk)){
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			if(jedis!=null)
				jedis.close();
		}
	}
	/**
	 * 取得对象
	 * @param key
	 * @param c
	 * @return
	 */
	public Object hgetAll(String key,Class c){
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			Map<String, String> map=jedis.hgetAll(key);
			return Util.mapToBean(c,map);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			if(jedis!=null)
				jedis.close();
		}
	}
	/**
	 * 设置保存时间
	 * @param key key
	 * @param seconds 秒
	 * @return
	 */
	public Long expire(String key,int seconds){
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			Long num=jedis.expire(key, seconds);
			return num;
		}catch(Exception e){
			e.printStackTrace();
			return 0L;
		}finally{
			if(jedis!=null)
				jedis.close();
		}
	}
	
	//http://doc.redisfans.com/index.html
	private static class Util{
		public static Object mapToBean(Class type, Map map) throws IntrospectionException, IllegalAccessException,InstantiationException, InvocationTargetException {
	        if(map.isEmpty() || map==null || map.size()==0){
	        	return null;
	        }			
			//BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
	        Object obj = type.newInstance(); // 创建 JavaBean 对象

	        // 给 JavaBean 对象的属性赋值
	        for (Object k:map.keySet()) {  
	            Object v = "";  
	            if (!(k+"").isEmpty()) {  
	                v = map.get(k);  
	            }  
	            Field[] fields = null;  
	            fields = obj.getClass().getDeclaredFields();  
	            //String clzName = obj.getClass().getSimpleName();  
	            for (Field field : fields) {  
	                int mod = field.getModifiers();  
	                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){  
	                    continue;  
	                }  
	                if (field.getName().equals(k)) {  
	                    field.setAccessible(true);  
	                    if(field.getType().toString().equals("int") || field.getType().toString().contains("Integer"))
	                    	field.set(obj, Integer.valueOf(v.toString()));  
	                    else if(field.getType().toString().equals("short")){
	                    	field.set(obj, Short.valueOf(v.toString()));  
	                    }else if(field.getType().toString().equals("char")){
	                    	field.set(obj,v.toString().toCharArray()[0]);  
	                    }else if(field.getType().toString().contains("Boolean")){
	                    	field.set(obj,Boolean.valueOf(v.toString()));  
	                    }else if(field.getType().toString().equals("double")){
	                    	field.set(obj,Double.valueOf(v.toString()));  
	                    }else if(field.getType().toString().equals("float")){
	                    	field.set(obj,Float.valueOf(v.toString()));  
	                    }else if(field.getType().toString().contains("Byte")){
	                    	field.set(obj,v.toString().getBytes()[0]);  
	                    }else if(field.getType().toString().equals("long") || field.getType().toString().contains("Long")){
	                    	field.set(obj,Long.valueOf(v.toString()));  
	                    }else if(field.getType().toString().contains("BigDecimal")){
	                    	field.set(obj,BigDecimal.valueOf(Double.valueOf(v.toString())));  
	                    }else
	                    	field.set(obj, v);
	                }  

	            }  
	        }  
	        return obj;
	    } 
	    public static Map<String, String> beanToMap(Object obj) {  
	        
	        Map<String, String> map = new HashMap<String, String>();  
	        // System.out.println(obj.getClass());  
	        // 获取f对象对应类中的所有属性域  
	        Field[] fields = obj.getClass().getDeclaredFields();  
	        for (int i = 0, len = fields.length; i < len; i++) {  
	            String varName = fields[i].getName();  
	            try {  
	                // 获取原来的访问控制权限  
	                boolean accessFlag = fields[i].isAccessible();  
	                // 修改访问控制权限  
	                fields[i].setAccessible(true);  
	                // 获取在对象f中属性fields[i]对应的对象中的变量  
	                Object o = fields[i].get(obj);  
	                if (o != null && !"serialVersionUID".equals(varName))  
	                    map.put(varName, o.toString());  
	                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
	                // 恢复访问控制权限  
	                fields[i].setAccessible(accessFlag);  
	            } catch (IllegalArgumentException ex) {  
	                ex.printStackTrace();  
	            } catch (IllegalAccessException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return map;  
	  
	    }  
	}

}
