//Table Model     更新表的作用
package com.ceshi;
import javax.swing.table.*;

import java.util.*;
import java.sql.*;
public class StuModel extends AbstractTableModel{
	Vector rowData,columNames;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public StuModel()
	{
		columNames=new Vector();
		rowData=new Vector();
		columNames.add("学号");
		columNames.add("姓名");
		columNames.add("性别");
		columNames.add("年龄");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","scott","tiger");
			ps=conn.prepareStatement("select * from stu");
			rs=ps.executeQuery();
			while(rs.next())
			{
				Vector hangdata=new Vector();
				hangdata.add(rs.getString(1));
				hangdata.add(rs.getString(2));
				hangdata.add(rs.getString(3));
				hangdata.add(rs.getInt(4));
				rowData.add(hangdata);
			}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void select(String sql)
	{
		columNames=new Vector();
		rowData=new Vector();
		columNames.add("学号");
		columNames.add("姓名");
		columNames.add("性别");
		columNames.add("年龄");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdba:oracle:thin:@localhost:1521:oracle","scott","tiger");
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Vector hangdata=new Vector();
				hangdata.add(rs.getString(1));
				hangdata.add(rs.getString(2));
				hangdata.add(rs.getString(3));
				hangdata.add(rs.getInt(4));
				rowData.add(hangdata);
			}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	

	
	//以下为继承抽象类里自带的方法函数
	
	//获得列数
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columNames.size();
	}

	//获得行数
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

	//取得二维表的每个元素
	public Object getValueAt(int arg0, int arg1) {
		
		//***********************************************
		return ((Vector)this.rowData.get(arg0)).get(arg1);
		//***********************************************
	}
	//获得列名称
	public String getColumnName(int arg0) {
		// TODO 自动生成的方法存根
		System.out.println((String)this.columNames.get(arg0));
		return (String)this.columNames.get(arg0);
	}

}
