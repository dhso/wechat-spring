package com.minws.pagination;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Condition {

	public static String PREFIX = "`";
	public static String SUBFIX = "`";
	private List<Object> columns;
	private Page page;
	private List<Object> groupBy = new ArrayList<Object>();
	private List<Order> orderBy = new ArrayList<Order>();
	private String superWhere;
	private List<Where> listWhere=new ArrayList<Where>();

	public List<Where> getListWhere() {
		return listWhere;
	}

	public void setWhere(Where ... wheres) {
		for(int i=0;i<wheres.length;i++){
			listWhere.add(wheres[i]);
		}
	}

	public String getSuperWhere() {
		return superWhere;
	}

	public void setSuperWhere(String superWhere) {
		this.superWhere = "WHERE " + superWhere;
	}

	public Condition setOrderBy(Order... orderBy) {
		for(int i=0;i<orderBy.length;i++){
			Order order=orderBy[i];
			order.setColumn(PREFIX+order.getColumn()+SUBFIX);
		}
		this.orderBy = Arrays.asList(orderBy);
		return this;
	}

	public Condition setOrderBy(String column, OrderType orderType) {
		column=PREFIX+column+SUBFIX;
		this.orderBy = Arrays.asList(new Order(column, orderType));
		return this;
	}

	public List<Order> getOrderBy() {
		return this.orderBy.size()>0?this.orderBy:null;
	}

	public void setGroupBy(Object ... columns) {
		for(int i=0;i<columns.length;i++){
			columns[i]=PREFIX+columns[i].toString()+SUBFIX;
		}
		groupBy = Arrays.asList(columns);
	}

	public List<Object> getGroupBy() {
		return this.groupBy.size()>0?this.groupBy:null;
	}

	class Page {
		private int offset;
		private int rows;

		public Page(int pageNo, int pageSize) {
			this.offset = pageNo > 1 ? (pageNo - 1) * pageSize : 0;
			this.rows = pageSize;
		}

		public int getOffset() {
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}

		public int getRows() {
			return rows;
		}

		public void setRows(int rows) {
			this.rows = rows;
		}
	}

	public void selectColumns(Object ... columns) {
		for(int i=0;i<columns.length;i++){
			columns[i]=PREFIX+columns[i].toString()+SUBFIX;
		}
		this.columns = Arrays.asList(columns);
	}

	public List<Object> getColumns() {
		return this.columns;
	}

	public void setPage(int pageNo, int pageSize) {
		page = new Page(pageNo, pageSize);
	}

	public Page getPage() {
		return this.page;
	}

	public String toSelectSql(String table) {

		String column = "";
		if (columns.size() > 0) {
			for (int i = 0, j = columns.size(); i < j; i++) {
				if (i == j - 1) {
					column += columns.get(i).toString();
				} else
					column += columns.get(i).toString() + ",";
			}
		} else {
			column = "*";
		}

		String sql = "select " + column + " FROM " +PREFIX +table +SUBFIX +" ";

		if (superWhere != null) {
			sql += superWhere;
		} else if (listWhere != null) {
			sql += "WHERE ";
			for (int i = 0; i < listWhere.size(); i++) {
				Where w = listWhere.get(i);
				switch (w.getType()) {
				case 1:
					sql += w.getParam1();
					break;
				case 2:
					sql += w.getParam1() + "" + w.getParam2();
					break;
				case 3:
					sql += w.getParam1() + "" + w.getParam2() + ""
							+ w.getParam3();
					break;
				case 4:
					sql += w.getParam1() + "" + w.getParam2() + ""
							+ w.getParam3() + "" + w.getParam4();
					break;
				case 5:
					sql += w.getParam1() + "" + w.getParam2() + ""
							+ w.getParam3() + "" + w.getParam4() + ""
							+ w.getParam5();
					break;
				}
			}
		}

		if (groupBy.size() > 0) {
			sql += " GROUP BY ";
			for (int i = 0, j = groupBy.size(); i < j; i++) {
				if (i == j - 1) {
					sql += groupBy.get(i).toString();
				} else {
					sql += groupBy.get(i) + ",";
				}
			}
		}
		if (orderBy.size() > 0) {
			sql += " ORDER BY ";
			for (int i = 0, j = orderBy.size(); i < j; i++) {
				Order ob = orderBy.get(i);
				if (i == j - 1) {
					sql += ob.getColumn() + " " + ob.getOrderType();
				} else {
					sql += ob.getColumn() + " " + ob.getOrderType() + " , ";
				}
			}
		}
		if (page != null) {
			sql += " LIMIT " + page.getOffset() + "," + page.getRows();
		}
		return sql;
	}
}