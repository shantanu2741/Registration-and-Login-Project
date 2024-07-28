package Dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;

public class DBConnection {
	public static Connection getConnection()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myLogininJSp","root","1234");		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}