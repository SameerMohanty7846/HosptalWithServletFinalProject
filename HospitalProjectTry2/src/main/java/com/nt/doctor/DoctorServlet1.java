package com.nt.doctor;

import jakarta.servlet.RequestDispatcher;
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

public class DoctorServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String verifyQuery="SELECT COUNT(*) AS count FROM doctor Where user=? AND password=?";


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String name=req.getParameter("name").trim();
		String pass=req.getParameter("password").trim();
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps=con.prepareStatement(verifyQuery);
			ps.setString(1, name); ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int x=rs.getInt("count");
				if(x==1) {
					HttpSession ses=req.getSession();
					ses.setAttribute("doc-name", name);
					ses.setAttribute("doc-pass", pass);
					pw.println("<div class=\"bg-info   text-white \" style=\"height: 350px; margin: 140px; font-size: 50px;padding: 120px; border-radius: 100px;\">\r\n"
							+ "        <span>USER VERIFIED SUCCESSFULLY</span><br>\r\n"
							+ "        <a href='"+res.encodeUrl("DoctorServlet2")+"'><button class=\"btn btn-warning\">PROFILE</button></a>\r\n"
							+ "\r\n"
							+ "    </div>\r\n"
							+ "    <span class=\"\"><a href=\"index.html\"><button class=\"btn btn-danger\">HOME</button></a></span>\r\n"
							+ "\r\n"
							+ "");
					
				
					
					
				}else {
					pw.println("  <marquee style=\"color:red\">INVALID USERID OR PASSWORD</marquee>\r\n");

					RequestDispatcher rd=req.getRequestDispatcher("doctorlogin.html");
					rd.include(req, res);


					

				}
			}
			
		}catch(Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("/ErrorServlet");
			rd.forward(req, res);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
