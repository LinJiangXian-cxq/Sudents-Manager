//主界面前后端
package com.ceshi;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.sql.*;
public class Stu extends JFrame implements ActionListener{
	JPanel jp1,jp3;
	JLabel jl1;
	JTable jt;
	JButton jb1,jb2,jb3,jb4;
	JTextField jtf;
	JScrollPane jsp;

	PreparedStatement ps=null;
	Connection conn=null;
	ResultSet rs=null;
	StuModel sm;
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Stu s=new Stu();
	}
	public Stu()
	{
		//************导入模型到JTable：把模型加进来使用******************************
		StuModel sm=new StuModel();
		jt=new JTable(sm);
		//******************************************
		
		
		//****************滚动条和JTable**************************
		jsp=new JScrollPane(jt);
		//******************************************
		
		jl1=new JLabel("姓名");
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jb1.setActionCommand("select");
		
		jp1=new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jb2=new JButton("增加");
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb3.setActionCommand("update");
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		jb4.setActionCommand("del");
		
		jp3=new JPanel();
		jp3.add(jb2);
		jp3.add(jb3);
		jp3.add(jb4);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp3,"South");

		
		
		this.setTitle("学生管理系统");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getActionCommand().equals("select"))
		{
			//trim()的作用是:去掉字符串左右的空格
			String name=jtf.getText().trim();
			String sql="";
			if(name.equals(""))
			{
				sql="select * from stu";
			}
			else
			{
				//*********************************************
				sql="select * from stu where sname='"+name+"'";
				//*********************************************
			}
			StuModel sm=new StuModel();
			sm.select(sql);
			//************更新模型类**************
			jt.setModel(sm);
			//**************************
		}
		else if(arg0.getActionCommand().equals("del"))
		{
			//1、得到学生的ID号
			//getSelectedRow会返回用户点中的行
			//如果该用户一行都没有选择，就会返回-1
			sm=new StuModel();
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//得到学生编号
			String stuId=sm.getValueAt(rowNum, 0).toString();
			//连接数据库，完成删除任务

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection("jdba:oracle:thin:@localhost:1521:oracle","scott","tiger");
				ps=conn.prepareStatement("delete from stu where sid=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//删除后重新查询全部
			sm=new StuModel();
			//更新sm模型
			jt.setModel(sm);
		}
		else if(arg0.getActionCommand().equals("add"))
		{
			StuAddDialog sad=new StuAddDialog(this,"添加学生信息",true);
			//删除后重新查询全部
			sm=new StuModel();
			//更新sm模型
			jt.setModel(sm);
		}
		else if(arg0.getActionCommand().equals("update"))
		{
			StuModel sm=new StuModel();
			int rownum=jt.getSelectedRow();
			if(rownum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行");
			}
			StuUpdateDialog sud=new StuUpdateDialog(this,"修改学生信息",true,sm,rownum);
			
			jt.setModel(sm);
		}
	}
}
