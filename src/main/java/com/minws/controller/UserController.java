package com.minws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minws.common.ResultCode;
import com.minws.common.ServiceResult;
import com.minws.common.WebResult;
import com.minws.model.User;
import com.minws.model.UserColumn;
import com.minws.pagination.Condition;
import com.minws.pagination.OrderType;
import com.minws.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	/**
	 * 根据id查询，返回数量，可能为null
	 * @param Long id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public String get(Long id){
		try{
			ServiceResult r=userService.get(id);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.successWithData(r.getData());
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 根据条件查询，返回数据列表
	 * @param Condition condition
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(){
		try{
			Condition condition=new Condition();
			//where email='XXX@Q.COM' and password='11111'
			condition.setWhere(UserColumn.EMAIL.eq("XXX@Q.COM"),UserColumn.AND(),UserColumn.PASSWORD.eq("11111"));
			//where email='XXX@Q.COM' or (email='xxx' and loginName='ccc')
			//condition.setWhere(UserColumn.EMAIL.eq("XXX@Q.COM"),UserColumn.OR(),UserColumn.LEFT_BRACKET(),UserColumn.EMAIL.eq("xxx"),UserColumn.AND(),UserColumn.LOGINNAME.eq("ccc"),UserColumn.RIGHT_BRACKET());
			//condition.setGroupBy(UserColumn.ID);
			//condition.setOrderBy(UserColumn.ID.DESC(),UserColumn.CREATETIME.ASC());
			//condition.setOrderBy(UserColumn.ID.toString(),OrderType.ASC);
			condition.setPage(1,1);
			//condition.setSuperWhere("id=1");
			
			ServiceResult r=userService.query(condition);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.successWithData(r.getData());
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 新增，返回是否成功
	 * @param User user
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(User user){
		try{
			ServiceResult r=userService.add(user);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 批量新增，返回是否成功
	 * @param List<User> list
	 * @return
	 */
	@RequestMapping("/batchAdd")
	@ResponseBody
	public String batchAdd(List<User> list){
		try{
			ServiceResult r=userService.batchAdd(list);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 根据id删除,返回是否成功
	 * @param Long id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id){
		try{
			ServiceResult r=userService.delete(id);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 根据id批量删除,返回是否成功
	 * @param List<User> list
	 * @return
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public String batchDelete(List<User> list){
		try{
			ServiceResult r=userService.batchDelete(list);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 更加主键更新非null的字段，返回成功与否
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateSelectiveColumnByPrimaryKey")
	@ResponseBody
	public String updateSelectiveColumnByPrimaryKey(User user){
		try{
			ServiceResult r=userService.updateSelectiveColumnByPrimaryKey(user);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
	 * 根据主键更新所有字段，返回成功与否
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateAllColumnByPrimaryKey")
	@ResponseBody
	public String updateAllColumnByPrimaryKey(User user){
		try{
			ServiceResult r=userService.updateAllColumnByPrimaryKey(user);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
	/**
     * 更加id批量更新,返回成功与否
     * 连接数据库要加 ：&allowMultiQueries=true 
     * @param List<User> list
     * @return
     */
	@RequestMapping("/batchUpdateSelectiveColumnByPrimaryKey")
	@ResponseBody
	public String batchUpdateSelectiveColumnByPrimaryKey(List<User> list){
		try{
			ServiceResult r=userService.batchUpdateSelectiveColumnByPrimaryKey(list);
			if(ResultCode.SUCCESS.equals(r.getCode())){
				//返回是否成功
				return WebResult.success();
			}else{
				//失败或异常
				return WebResult.fail(r.getCode(),r.getMsg());
			}
		}catch(Exception e){
			return WebResult.exception(e);
		}
	}
}