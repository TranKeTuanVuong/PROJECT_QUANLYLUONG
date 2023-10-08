package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static Connection con =null;
	public static ConnectDB instance = new ConnectDB();
	
	
	public static Connection getConnection() {
		return con;
	}

	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() throws SQLException{
			String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLiLuong";
			String user = "sa";
			String pwd = "123456";
			con = DriverManager.getConnection(url,user,pwd);
	}
	
	public void disconnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	
}
