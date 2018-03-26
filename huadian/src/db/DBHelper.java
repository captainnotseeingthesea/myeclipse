package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sun.xml.internal.ws.message.StringHeader;

public class DBHelper {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/huadian?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
	private static final String username = "root";
	private static final String password = "991227";
	private static Connection conn = null;
	private static int x = 1;
	//  连接驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 单例模式返回数据库连接对象
	public static Connection getConnection() throws SQLException {
		if (conn == null)
			conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void main(String[] args) {

		try {
			conn = DBHelper.getConnection();
			if (conn != null) {
				System.out.println("数据库连接正常!");
				java.sql.Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("select name,password from user");
				while (rs.next()) {
					System.out.println(rs.getString("name") + ","
							+ rs.getString("password"));
				}
			} else {
				System.out.println("数据库连接异常!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
