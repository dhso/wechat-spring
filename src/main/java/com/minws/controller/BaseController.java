package com.minws.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.minws.common.GlobalConstant;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpSession session;
	
	public BaseController() {

	}
	public String getRealPath(){
		return session.getServletContext().getRealPath("/");
	}
	public String getHttpDomain(){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	}
	public String getHttpPath(){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	public Boolean checkCaptcha(String captcha){
		if(session.getAttribute(GlobalConstant.CAPTCHA)!=null && captcha!=null && captcha.equals(session.getAttribute(GlobalConstant.CAPTCHA)+"")){
			return true;
		}
		return false;
	}	
	public void setCharset(){
		//这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
		response.setCharacterEncoding("UTF-8"); 
	}
	public String generImageFileName(MultipartFile file){
        String expandedName="";
        if (file.getContentType().equals("image/pjpeg")
				|| file.getContentType().equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (file.getContentType().equals("image/png")
				|| file.getContentType().equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (file.getContentType().equals("image/gif")) {
			expandedName = ".gif";
		} else if (file.getContentType().equals("image/bmp")) {
			expandedName = ".bmp";
		}
        if(!"".equals(expandedName))
        	return UUID.randomUUID().toString()+expandedName;
        else
        	return null;
	}
	public String getToken(){
		return UUID.randomUUID().toString().replaceAll("-","")+"&t="+new Date().getTime();
	}
}
