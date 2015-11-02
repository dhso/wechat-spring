package com.minws.model;

import java.io.Serializable;

public class User extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private Long createTime;
	/**
	 * 是否已验证手机号码
	 */
	private Integer isVerifyPhone;
	/**
	 * 申请验证手机号码时间
	 */
	private Long applyVerifyPhoneTime;
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 验证token
	 */
	private String verifyToken;
	/**
	 * 是否验证邮箱
	 */
	private Integer isVerifyEmail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getIsVerifyPhone() {
		return isVerifyPhone;
	}
	public void setIsVerifyPhone(Integer isVerifyPhone) {
		this.isVerifyPhone = isVerifyPhone;
	}
	public Long getApplyVerifyPhoneTime() {
		return applyVerifyPhoneTime;
	}
	public void setApplyVerifyPhoneTime(Long applyVerifyPhoneTime) {
		this.applyVerifyPhoneTime = applyVerifyPhoneTime;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getVerifyToken() {
		return verifyToken;
	}
	public void setVerifyToken(String verifyToken) {
		this.verifyToken = verifyToken;
	}
	public Integer getIsVerifyEmail() {
		return isVerifyEmail;
	}
	public void setIsVerifyEmail(Integer isVerifyEmail) {
		this.isVerifyEmail = isVerifyEmail;
	}
	
	@Override
	public String toString() {		
		return new StringBuilder()
		.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode()))
		.append("[")
        .append("id=").append(id).append(",")
        .append("loginName=").append(loginName).append(",")
        .append("email=").append(email).append(",")
        .append("mobilePhone=").append(mobilePhone).append(",")
        .append("password=").append(password).append(",")
        .append("createTime=").append(createTime).append(",")
        .append("isVerifyPhone=").append(isVerifyPhone).append(",")
        .append("applyVerifyPhoneTime=").append(applyVerifyPhoneTime).append(",")
        .append("headImage=").append(headImage).append(",")
        .append("verifyToken=").append(verifyToken).append(",")
        .append("isVerifyEmail=").append(isVerifyEmail)
        .append("]")
        .toString();
	}
}