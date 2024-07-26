package com.nt.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TotalRevenueServlet extends HttpServlet {
	private static final String GETTOTALINCOME="SELECT SUM(fees) As total_fees FROM totalincome";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		try {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				System.out.println("Connection Created");
				PreparedStatement ps=con.prepareStatement(GETTOTALINCOME);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					int income=rs.getInt("total_fees");
					pw.println("<div class=\"bg-info   text-white \" style=\"height: 350px; margin: 140px; font-size: 50px;padding: 120px; border-radius: 100px;\">\r\n"
							+ "        <span style=\"margin-left: 250px;\">TOTAL REVENUE</span><br>\r\n"
							+ "        <span style=\"color: red;margin-left: 300px; font-size: 80px;font-weight: bold;font-style: italic;text-decoration: underline;\">"+income+"/-</span>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "    </div>\r\n"
							+ "    <span class=\"\"><a href=\"Servlet2\"><button class=\"btn btn-danger\">HOME</button></a></span>\r\n"
							+ "");
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
