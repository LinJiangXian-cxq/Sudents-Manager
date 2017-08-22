//Table Model     ���±������
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
		columNames.add("ѧ��");
		columNames.add("����");
		columNames.add("�Ա�");
		columNames.add("����");
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public void select(String sql)
	{
		columNames=new Vector();
		rowData=new Vector();
		columNames.add("ѧ��");
		columNames.add("����");
		columNames.add("�Ա�");
		columNames.add("����");
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	

	
	//����Ϊ�̳г��������Դ��ķ�������
	
	//�������
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return this.columNames.size();
	}

	//�������
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.rowData.size();
	}

	//ȡ�ö�ά���ÿ��Ԫ��
	public Object getValueAt(int arg0, int arg1) {
		
		//***********************************************
		return ((Vector)this.rowData.get(arg0)).get(arg1);
		//***********************************************
	}
	//���������
	public String getColumnName(int arg0) {
		// TODO �Զ����ɵķ������
		System.out.println((String)this.columNames.get(arg0));
		return (String)this.columNames.get(arg0);
	}

}
