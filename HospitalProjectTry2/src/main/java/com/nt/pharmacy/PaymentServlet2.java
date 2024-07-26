package com.nt.pharmacy;

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
import java.sql.Timestamp;


public class PaymentServlet2 extends HttpServlet {
	private static final String INSERT="INSERT into totalincome(regno,method,fees,time) values(?,?,?,?)"; 
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		String method=req.getParameter("method");
		int bill=Integer.parseInt(req.getParameter("bill"));
		String reg=req.getParameter("reg");
		Timestamp time=new Timestamp(System.currentTimeMillis());

		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps=con.prepareStatement(INSERT);
			ps.setString(1, reg);
			ps.setString(2, method);
			ps.setInt(3, bill);
			ps.setTimestamp(4, time);

			int x=ps.executeUpdate();
			if(x==1) {
				pw.println("<div class=\"bg-info   text-white \" style=\"height: 350px; margin: 140px; font-size: 50px;padding: 120px; border-radius: 100px;\">\r\n"
						+ "        <span>AMOUNT PAID SUCCESSFULLY</span><br>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "    </div>\r\n"
						+ "    <span class=\"\"><a href=\"PharmacyServlet2\"><button class=\"btn btn-danger\">HOME</button></a></span>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "    ");
			}else {
				pw.println("<p>ISSUE IN TRANSACTION</p>");

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
