package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author adanac@sina.com
 * @date 2015-10-20下午11:19:16
 * @version v1.0
 */
public class DbUtil {

	private String dbUrl = "jdbc:mysql://localhost:3306/db_rbps";
	private String dbUserName = "root";
	private String dbPassword = "root123";
	private String jdbcName = "com.mysql.jdbc.Driver";

	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}

	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
