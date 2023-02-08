package com.nayan.sahare;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/list")


public class FetchServlet  extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		Properties props=new  Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		
		Connection conn=DBConfig.getConnection(props);
		
		
		//String param=req.getParameter("id");
		
		if(conn!=null) {
			out.print("Connection Established");
			out.print("<br>");
			out.print("<br>");
			out.print("<br>");
			out.print("<br>");
			
			Statement stmt;
			
			try {
				
				int id =Integer.parseInt(req.getParameter("pid"));
		
				stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from Products  ");
				
				
				while(rs.next()) {
					
					if(rs.getInt("id")==id)
					{					out.print("<br>"+rs.getInt(1)+", "+"      "+rs.getString(2)+",          "+rs.getBigDecimal(3)+","+rs.getTimestamp(4)+"<br>");
					
				}
				}
				out.println("<h3><a href='index.html'> go back to  home page</a></h3>" );

				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			out.println("Error While Connecting");
					
			out.println("<h3><a href='index.html'> go back to  home page</a></h3>" );
		
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
