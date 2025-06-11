package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet{

	
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException
	{
		Connection con;
		PreparedStatement pstmt;
		String url="jdbc:mysql://localhost:3306/jee4";
		String username="root";
		String password="Charitha@17";
		
		String sql="INSERT INTO `members`(`firstname`,`lastname`,`email`) values(?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection(url,username,password);
			 pstmt=con.prepareStatement(sql);
			 pstmt.setString(1, req.getParameter("firstname"));
			 pstmt.setString(2, req.getParameter("lastname"));
			 pstmt.setString(3, req.getParameter("email"));
			int i= pstmt.executeUpdate();	
			PrintWriter out=resp.getWriter();
			if(i>0)
			{
				out.print("REGISTRATION SUCCESSFUL");
				
				
			}
			else {
				out.print("REGISTRATION FAILURE");
			}
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
