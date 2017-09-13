package com.xjb.utils;
/**
 * �������ݿ��һ��������
 */
import java.sql.*;


public class sqlHelper {
	//������Ҫ�ı���
	static Connection ct=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String url="jdbc:sqlserver://localhost:1433;database=";
	static String database="SIM";
	static String user="sa";
	static String password="952999";
	
	
	//��ʼ������
	static {
		//��������
		try {
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//ִ�в�ѯ���з��ؼ���
	public static ResultSet excuteQuery(String sql,String parameters[]){
		
		try {
			ct=DriverManager.getConnection(url+database,user,password);
			ps=ct.prepareStatement(sql);
			
			if(parameters==null||parameters.equals("")){
				rs=ps.executeQuery();
			}else{
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i+1, parameters[i]);
				}
				rs=ps.executeQuery();	
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	//�޷��ؼ��ģ�ִ����ɾ��
	public static boolean excuteUpdate(String sql,String parameters[]){
		boolean b=false;
		try {
			ct=DriverManager.getConnection(url+database,user,password);
			ps=ct.prepareStatement(sql);
			
			if(parameters==null||parameters.equals("")){
				if(ps.executeUpdate()==1) b=true;
			}else{
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i+1, parameters[i]);
				}
				if(ps.executeUpdate()==1) b=true;		 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		}
		
		return b;
		
	}
	
	public static Connection getCt() {
		return ct;
	}

	public static void setCt(Connection ct) {
		sqlHelper.ct = ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static void setPs(PreparedStatement ps) {
		sqlHelper.ps = ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		sqlHelper.rs = rs;
	}

	
	
	//�ر���Դ�ķ���
	public static void closesqlHelper(ResultSet rs,PreparedStatement ps,Connection ct){
		try {
			if(rs!=null){
			rs.close();
			rs=null;
		}
		if(ps!=null){
			ps.close();
			ps=null;
		}
		if(ct!=null){
			ct.close();
			ct=null;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
