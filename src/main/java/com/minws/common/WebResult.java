package com.minws.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * web层返回数据给浏览器
 * @author Administrator
 *
 */
public class WebResult implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1180689741530810743L;
	
	private static final SerializerFeature feature = SerializerFeature.WriteMapNullValue;
	private static final SerializerFeature feature2 = SerializerFeature.WriteNullStringAsEmpty;
	
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
	/**
	 * 时间
	 */
	private Long time=System.currentTimeMillis();
	
	private WebResult(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	private WebResult(String msg) {
		this.msg=msg;
	}
	
	private WebResult(String code,Object data,String msg) {
		this.code=code;
		this.data=data;
		this.msg=msg;
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	public static String success(){
		WebResult r=new WebResult(ResultCode.SUCCESS,"成功");		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回成功
	 * @return
	 */
	public static String success(String code,String msg){
		WebResult r=new WebResult(code,msg);		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回成功，并带提示语
	 * @param msg
	 * @return
	 */
	public static String successWithMsg(String msg){
		WebResult r=new WebResult(ResultCode.SUCCESS,msg);		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回成功，并带数据
	 * @param data
	 * @return
	 */
	public static String successWithData(Object data){
		WebResult r=new WebResult(ResultCode.SUCCESS,data,"成功");		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回成功，并带数据和提示语
	 * @param data
	 * @param msg
	 * @return
	 */
	public static String successWithDataMsg(Object data,String msg){
		WebResult r=new WebResult(ResultCode.SUCCESS,data,msg);		
		return JSON.toJSONString(r,feature,feature2);
	}
	
	/**
	 * 返回失败
	 * @return
	 */
	public static String fail(){
		WebResult r=new WebResult(ResultCode.FAIL,"失败");		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回失败，并带提示语
	 * @param msg
	 * @return
	 */
	public static String fail(String msg){
		WebResult r=new WebResult(ResultCode.FAIL,msg);		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回失败，并带提示语
	 * @param msg
	 * @return
	 */
	public static String fail(String code,String msg){
		WebResult r=new WebResult(code,msg);		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回失败，并带数据
	 * @param data
	 * @return
	 */
	public static String failWithData(Object data){
		WebResult r=new WebResult(ResultCode.FAIL,data,"失败");
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 返回失败，并带数据和提示语
	 * @param data
	 * @param msg
	 * @return
	 */
	public static String failWithDataMsg(Object data,String msg){
		WebResult r=new WebResult(ResultCode.FAIL,data,msg);		
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 抛异常
	 * @param msg
	 * @return
	 */
	public static String exception(String msg){
		WebResult r=new WebResult(ResultCode.EXCEPTION,msg);	
		return JSON.toJSONString(r,feature,feature2);
	}
	/**
	 * 抛异常
	 * @param msg
	 * @return
	 */
	public static String exception(Exception e){
		e.printStackTrace();
		WebResult r=new WebResult(ResultCode.EXCEPTION,"控制层系统异常");	
		return JSON.toJSONString(r,feature,feature2);
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

	public Long getTime() {
		return time;
	}
	
}
