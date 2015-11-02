package com.minws.model;

import com.minws.pagination.Condition;
import com.minws.pagination.Order;
import com.minws.pagination.OrderType;
import com.minws.pagination.Where;

public enum UserColumn {

	ID("id"),
	LOGINNAME("login_name"),
	EMAIL("email"),
	MOBILEPHONE("mobile_phone"),
	PASSWORD("password"),
	CREATETIME("create_time"),
	ISVERIFYPHONE("is_verify_phone"),
	APPLYVERIFYPHONETIME("apply_verify_phone_time"),
	HEADIMAGE("head_image"),
	VERIFYTOKEN("verify_token"),
	ISVERIFYEMAIL("is_verify_email");
	
	private String key;
	
	public String getValue(){
		return this.key;
	}
	private UserColumn(String key){
		this.key=key;
	}
		
	public Order order(OrderType orderType){
		return new Order(this.key,orderType);
	}
	
	public Order DESC(){
		return new Order(this.key,OrderType.DESC);
	}
	
	public Order ASC(){
		return new Order(this.key,OrderType.ASC);
	}
	
	//1
	public static Where OR(){
		return new Where(" OR ",1);
	}
	public static Where AND(){
		return new Where(" AND ",1);
	}
	public Where isNull(){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" IS NULL",1);
	}
	public Where isNotNull(){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" IS NOT NULL",1);
	}
	public static Where LEFT_BRACKET(){
		return new Where("(",1);
	}
	public static Where RIGHT_BRACKET(){
		return new Where(")",1);
	} 
	//2
	public Where eq(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"=",value,2);
	}
	public Where geq(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+">=",value,2);
	}
	public Where gt(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+">",value,2);
	}
	public Where lt(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"<",value,2);
	}
	public Where let(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"<=",value,2);
	}
	public Where net(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"!=",value,2);
	}
	public Where lgq(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"<>",value,2);
	}
	public Where ngq(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"!>",value,2);
	}
	public Where nlq(Object value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+"!<",value,2);
	}
	
	//3
	public Where IN(Long ... values){
		String in="";
		for(int i=0;i<values.length;i++){
			if(i==values.length-1){
				in+=values[i];
			}else
				in+=values[i]+",";
		}
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" IN (",in,")",3);
	}
	public Where IN(String ... values){
		String in="";
		for(int i=0;i<values.length;i++){
			if(i==values.length-1){
				in+="'"+values[i]+"'";
			}else
				in+="'"+values[i]+"',";
		}
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" IN (",in,")",3);		
	}
	public Where NotIN(Long ... values){
		String in="";
		for(int i=0;i<values.length;i++){
			if(i==values.length-1){
				in+=values[i];
			}else
				in+=values[i]+",";
		}
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" NOT IN (",in,")",3);
	}
	public Where NotIN(String ... values){
		String in="";
		for(int i=0;i<values.length;i++){
			if(i==values.length-1){
				in+="'"+values[i]+"'";
			}else
				in+="'"+values[i]+"',";
		}
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" NOT IN (",in,")",3);	
	}
	public Where like(String value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" LIKE CONCAT('%',",value,",'%' )",3);
	}
	public Where NotLike(String value){
		return new Where(Condition.PREFIX+this.key+Condition.SUBFIX+" NOT LIKE CONCAT('%',",value,",'%' )",3);
	}
	//5
	public Where betweenAnd(Object value1,Object value2){
		return new Where("("+Condition.PREFIX+this.key+Condition.SUBFIX+" BETWEEN",value1," AND ",value2,")",5);
	}
	public Where NotBetweenAnd(Object value1,Object value2){
		return new Where("("+Condition.PREFIX+this.key+Condition.SUBFIX+" NOT BETWEEN",value1," AND ",value2,")",5);
	}
	public Where geqleq(Object value1,Object value2){
		return new Where("("+Condition.PREFIX+this.key+Condition.SUBFIX+">=",value1," AND "+Condition.PREFIX+this.key+Condition.SUBFIX+"<=",value2,")",5);
	}	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getValue();
	}
}
