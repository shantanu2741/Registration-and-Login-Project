package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import Dbconn.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myForm")
public class Register1 extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		String name=request.getParameter("name1");
		String email=request.getParameter("email1");
		String pass=request.getParameter("pass1");
		String gender=request.getParameter("gender1");
		String city=request.getParameter("city1");
		
		PrintWriter out=response.getWriter();
		
		
		try {
			Connection con1=DBConnection.getConnection();
			PreparedStatement ps=con1.prepareStatement("insert into register1 values(?,?,?,?,?)");
			
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,pass);
			ps.setString(4,gender);
			ps.setString(5,city);

			int i=ps.executeUpdate();
			if(i>0)
			{
				response.setContentType("text/html");
				out.print("<h3 style= 'color:green'>Registerd Successfully</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
			else {
				response.setContentType("text/html");
				out.print("<h3 style= 'color:red'>Registration Failed</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("/Register.jsp");
				rd.include(request, response);
			}
		}
		catch(Exception e)
		{
			out.print(e);
			response.setContentType("text/html");
			out.print("<h3 style= 'color:red'>Registration Failed<h3/>");
			RequestDispatcher rd=request.getRequestDispatcher("/Register.jsp");
			rd.include(request, response);
		}
				
	}
}
