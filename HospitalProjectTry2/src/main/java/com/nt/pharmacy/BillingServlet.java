package com.nt.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BillingServlet extends HttpServlet {
	public static final String GETDIAGNOSEDATA="select diagnose from diagnosedata where regno=?";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		String regno=req.getParameter("regno");
		HttpSession ses=req.getSession();
		ses.setAttribute("regno",regno );
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");

			PreparedStatement ps=con.prepareStatement(GETDIAGNOSEDATA);
			ps.setString(1, regno);
			ResultSet rs=ps.executeQuery();
			pw.println("<div class=\"h-25 w-50 border border-dark p-4 m-4\" >");
			while(rs.next()) {
				String data=rs.getString("diagnose").toUpperCase();
				pw.println("<p>"+data+"</p>");
			}
			pw.println("</div>");
			
			pw.println("<div>\r\n"
					+ "        <form action=\"BillingServlet2\" method ='post'  class=\" w-50 p-3 m-3 border border-warning border-3\">\r\n"
					+ "            <div class=\"d-flex w-100 p-2 m-2\">\r\n"
					+ "                <input type=\"text\" name=\"medicine-1\"class=\"form-control m-3 \" placeholder=\"medicine\"> <input type=\"text\" class=\"form-control m-3\"name=\"qty-1\" placeholder=\"qty\"><br>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"d-flex w-100 p-2 m-2\">\r\n"
					+ "                <input type=\"text\" name=\"medicine-2\"class=\"form-control m-3 \" placeholder=\"medicine\"> <input type=\"text\" class=\"form-control m-3\"name=\"qty-2\" placeholder=\"qty\"><br>\r\n"
					+ "                \r\n"
					+ "            </div><div class=\"d-flex w-100 p-2 m-2\">\r\n"
					+ "                <input type=\"text\" name=\"medicine-3\"class=\"form-control m-3 \" placeholder=\"medicine\"> <input type=\"text\" class=\"form-control m-3\"name=\"qty-3\" placeholder=\"qty\"><br>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"d-flex w-100 p-2 m-2\">\r\n"
					+ "                <input type=\"text\" name=\"medicine-4\"class=\"form-control m-3 \" placeholder=\"medicine\"> <input type=\"text\" class=\"form-control m-3\"name=\"qty-4\" placeholder=\"qty\"><br>\r\n"
					+ "                \r\n"
					+ "            </div><div class=\"d-flex w-100 p-2 m-2\">\r\n"
					+ "                <input type=\"text\" name=\"medicine-5\"class=\"form-control m-3 \" placeholder=\"medicine\"> <input type=\"text\" class=\"form-control m-3\"name=\"qty-5\" placeholder=\"qty\"><br>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "            <div>\r\n"
					+ "                <input type=\"text\" name=\"total\"class=\"form-control m-3 w-50 \" placeholder=\"total number of medicine\"> <br>\r\n"
					+ "            </div>\r\n"
					+ "            <div>\r\n"
					+ "                <button type=\"submit\" class=\"btn btn-warning mt-3\">GENERATE BILL</button>\r\n"
					+ "\r\n"
					+ "            </div>\r\n"
					+ "            \r\n"
					+ "            \r\n"
					+ "        </form>\r\n"
					+ "    </div>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
