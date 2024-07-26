package com.nt.doctor;

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


public class DiagnoseData extends HttpServlet {
	private static final String INSERTQUERY="INSERT INTO diagnosedata(regno,diagnose) values(?,?)";
	private static final String DELETEREGNO="delete  from doctorassignment where doc=? and regno=?";

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		HttpSession ses2=req.getSession();
		String regno=(String) ses2.getAttribute("reg");
		String docName=(String) ses2.getAttribute("doc-doc");
		String diagnoseData=req.getParameter("diagnose");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps=con.prepareStatement(INSERTQUERY);
			ps.setString(1, regno);
			ps.setString(2, diagnoseData);
			PreparedStatement ps2=con.prepareStatement(DELETEREGNO);
			ps2.setString(1, docName);
			ps2.setString(2, regno);

			int x=ps.executeUpdate();
			if(x==1) {
				int y=ps2.executeUpdate();
				if(y==1) {
					pw.println("<div class=\"bg-info   text-white \" style=\"height: 350px; margin: 140px; font-size: 50px;padding: 120px; border-radius: 100px;\">\r\n"
							+ "        <span>PATIENT DIAGNOSIS COMPLETED SUCCESSFULLY</span><br>\r\n"
							+ "\r\n"
							+ "    </div>\r\n"
							+ "    <span class=\"\"><a href=\"AllotedPatient\"><button class=\"btn btn-danger\">CLICK TO DIAGNOSIS MORE PATIENT</button></a></span>\r\n"
							+ "\r\n"
							+ "");

				}
				
				
			}else {
				pw.println("<p>ISSUE WITH DATABASE</p>");

				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		
	}
	
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
