package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dbconn.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import register11.User;

@WebServlet("/LoginForm")
public class Loginn extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		String name=request.getParameter("name1");
		String pass=request.getParameter("pass1");
		
		
		PrintWriter out=response.getWriter();
		
		
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from register1 where name=? and  password=?");
			
			ps.setString(1,name);
			ps.setString(2,pass);
 
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				User user=new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));

				HttpSession session=request.getSession();
				session.setAttribute("reference", user);
				
				response.setContentType("text/html");
				out.print("<h3 style= 'color:green'>Login Successfully</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Profile.jsp");
				rd.forward(request, response);
			}
			else {
				response.setContentType("text/html");
				out.print("<h3 style= 'color:red'>Login Failed</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
				rd.include(request, response);
			}
		}
		catch(Exception e)
		{
			out.print(e);
//			response.setContentType("text/html");
//			out.print("<h3 style= 'color:red'>Login Failed<h3/>");
//			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
//			rd.include(request, response);
		}
				
	}
}
