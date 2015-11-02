package com.minws.common;


/**
 * 服务层返回数据给web层
 * @author Administrator
 *
 */
public class ServiceResult implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8780273151222540130L;
	/**
	 * 状态码
	 */
	private String code;
	/**
	 * 返回的数据
	 */
	private Object data;
	/**
	 * 提示语
	 */
	private String msg;
	
	private ServiceResult(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	private ServiceResult(String msg) {
		this.msg=msg;
	}
	
	private ServiceResult(String code,Object data,String msg) {
		this.code=code;
		this.data=data;
		this.msg=msg;
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	public static ServiceResult success(){
		return new ServiceResult(ResultCode.SUCCESS,"成功");	
	}
	/**
	 * 返回成功，并带提示语
	 * @param msg
	 * @return
	 */
	public static ServiceResult successWithMsg(String msg){
		return new ServiceResult(ResultCode.SUCCESS,msg);	
	}
	/**
	 * 返回成功，并带数据
	 * @param data
	 * @return
	 */
	public static ServiceResult successWithData(Object data){
		return new ServiceResult(ResultCode.SUCCESS,data,"成功");
	}
	/**
	 * 返回成功，并带数据和提示语
	 * @param data
	 * @param msg
	 * @return
	 */
	public static ServiceResult successWithDataMsg(Object data,String msg){
		return new ServiceResult(ResultCode.SUCCESS,data,msg);	
	}
	
	/**
	 * 返回失败
	 * @return
	 */
	public static ServiceResult fail(){
		return new ServiceResult(ResultCode.FAIL,"失败");
	}
	/**
	 * 返回失败，并带提示语
	 * @param msg
	 * @return
	 */
	public static ServiceResult fail(String msg){
		return new ServiceResult(ResultCode.FAIL,msg);	
	}
	/**
	 * 返回失败，并带数据
	 * @param data
	 * @return
	 */
	public static ServiceResult failWithData(Object data){
		return new ServiceResult(ResultCode.FAIL,data,"失败");
	}
	/**
	 * 返回失败，并带数据和提示语
	 * @param data
	 * @param msg
	 * @return
	 */
	public static ServiceResult failWithDataMsg(Object data,String msg){
		return new ServiceResult(ResultCode.FAIL,data,msg);
	}
	/**
	 * 抛异常
	 * @param msg
	 * @return
	 */
	public static ServiceResult exception(Exception e){
		e.printStackTrace();
		return new ServiceResult(ResultCode.EXCEPTION,"服务层系统异常");
	}
	public String getCode() {
		return code==null?ResultCode.FAIL:code;
	}

	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}
	
}
