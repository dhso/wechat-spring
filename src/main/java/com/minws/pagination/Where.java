package com.minws.pagination;

public class Where {

	//private List<Where> listWhere=new ArrayList<Where>();
	
	private Object param1;
	private Object param2;
	private Object param3;
	private Object param4;
	private Object param5;
	private int type;
	
	public Where(){
		
	}
	
	public Where(Object param1,int type){
		this.param1=param1;
		this.type=type;
	}
	
	public Where(Object param1,Object param2,int type){
		this.param1=param1;
		this.param2=param2;
		this.type=type;
	}
	
	public Where(Object param1,Object param2,Object param3,int type){
		this.param1=param1;
		this.param2=param2;
		this.param3=param3;
		this.type=type;
	}
	
	public Where(Object param1,Object param2,Object param3,Object param4,int type){
		this.param1=param1;
		this.param2=param2;
		this.param3=param3;
		this.param4=param4;
		this.type=type;
	}
	
	public Where(Object param1,Object param2,Object param3,Object param4,Object param5,int type){
		this.param1=param1;
		this.param2=param2;
		this.param3=param3;
		this.param4=param4;
		this.param5=param5;
		this.type=type;
	}

	/*public List<Where> getListWhere() {
		return listWhere;
	}
*/
	public Object getParam1() {
		return param1;
	}

	public Object getParam2() {
		return param2;
	}

	public Object getParam3() {
		return param3;
	}

	public Object getParam4() {
		return param4;
	}

	public Object getParam5() {
		return param5;
	}
	public int getType() {
		return type;
	}
	
}
