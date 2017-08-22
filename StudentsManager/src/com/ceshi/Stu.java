//������ǰ���
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
		// TODO �Զ����ɵķ������
		Stu s=new Stu();
	}
	public Stu()
	{
		//************����ģ�͵�JTable����ģ�ͼӽ���ʹ��******************************
		StuModel sm=new StuModel();
		jt=new JTable(sm);
		//******************************************
		
		
		//****************��������JTable**************************
		jsp=new JScrollPane(jt);
		//******************************************
		
		jl1=new JLabel("����");
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jb1.setActionCommand("select");
		
		jp1=new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jb2=new JButton("����");
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb3.setActionCommand("update");
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		jb4.setActionCommand("del");
		
		jp3=new JPanel();
		jp3.add(jb2);
		jp3.add(jb3);
		jp3.add(jb4);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp3,"South");

		
		
		this.setTitle("ѧ������ϵͳ");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getActionCommand().equals("select"))
		{
			//trim()��������:ȥ���ַ������ҵĿո�
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
			//************����ģ����**************
			jt.setModel(sm);
			//**************************
		}
		else if(arg0.getActionCommand().equals("del"))
		{
			//1���õ�ѧ����ID��
			//getSelectedRow�᷵���û����е���
			//������û�һ�ж�û��ѡ�񣬾ͻ᷵��-1
			sm=new StuModel();
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//�õ�ѧ�����
			String stuId=sm.getValueAt(rowNum, 0).toString();
			//�������ݿ⣬���ɾ������

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection("jdba:oracle:thin:@localhost:1521:oracle","scott","tiger");
				ps=conn.prepareStatement("delete from stu where sid=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			//ɾ�������²�ѯȫ��
			sm=new StuModel();
			//����smģ��
			jt.setModel(sm);
		}
		else if(arg0.getActionCommand().equals("add"))
		{
			StuAddDialog sad=new StuAddDialog(this,"���ѧ����Ϣ",true);
			//ɾ�������²�ѯȫ��
			sm=new StuModel();
			//����smģ��
			jt.setModel(sm);
		}
		else if(arg0.getActionCommand().equals("update"))
		{
			StuModel sm=new StuModel();
			int rownum=jt.getSelectedRow();
			if(rownum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
			}
			StuUpdateDialog sud=new StuUpdateDialog(this,"�޸�ѧ����Ϣ",true,sm,rownum);
			
			jt.setModel(sm);
		}
	}
}
