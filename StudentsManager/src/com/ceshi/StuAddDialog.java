package com.ceshi;

import javax.swing.JDialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.sql.*;
public class StuAddDialog extends JDialog implements ActionListener{
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JButton jb1,jb2;
	
	Connection conn;
	PreparedStatement ps;
	
	public StuAddDialog(Frame owner,String title,boolean modal)
	{
		super(owner,title,modal);
		jl1=new JLabel("学号sid");
		jl2=new JLabel("姓名sname");
		jl3=new JLabel("性别ssex");
		jl4=new JLabel("年龄sage");
		
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jtf3=new JTextField(10);
		jtf4=new JTextField(10);
		
		jb1=new JButton("添加");
		jb1.addActionListener(this);
		jb1.setActionCommand("enter");
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jb2.setActionCommand("ext");
		
		this.setLayout(new GridLayout(5,2));
		this.add(jl1);
		this.add(jtf1);
		this.add(jl2);
		this.add(jtf2);
		this.add(jl3);
		this.add(jtf3);
		this.add(jl4);
		this.add(jtf4);
		this.add(jb1);
		this.add(jb2);
		

		this.setSize(150, 250);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		StuModel sm=new StuModel();
		if(arg0.getActionCommand().equals("enter"))
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","scott","tiger");
				ps=conn.prepareStatement("insert into stu values(?,?,?,?)");
				ps.setString(1, jtf1.getText().trim());
				ps.setString(2, jtf2.getText().trim());
				ps.setString(3, jtf3.getText().trim());
				ps.setInt(4, Integer.parseInt(jtf4.getText().trim()));
				ps.executeUpdate();
				this.dispose();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		if(arg0.getActionCommand().equals("ext"))
		{
			this.dispose();
		}
	}
}
