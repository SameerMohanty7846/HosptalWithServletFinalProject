package com.nt.assignPatient;

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

public class AssignPatient2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSERTDATA="INSERT INTO doctorassignment(regno,doc) values(?,?)";
	private static final String DELETEPATIENT ="DELETE  from patientforallocation where regno=?";


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		
		String regNo=req.getParameter("regno");
		String docName=req.getParameter("doc");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");

			PreparedStatement ps=con.prepareStatement(INSERTDATA);
			ps.setString(1, regNo);
			ps.setString(2, docName);
			int x=ps.executeUpdate();
			if(x==1) {
				PreparedStatement ps2=con.prepareStatement(DELETEPATIENT);
				ps2.setString(1, regNo);
				int y=ps2.executeUpdate();
				if(y==1) {
					pw.println("<div class=\"bg-info   text-white \" style=\"height: 350px; margin: 140px; font-size: 50px;padding: 120px; border-radius: 100px;\">\r\n"
							+ "        <span>PATIENT ASSIGNED  SUCCESSFULLY</span><br>\r\n"
							+ "\r\n"
							+ "    </div>\r\n"
							+ "    <span class=\"\"><a href=\"AssignPatient\"><button class=\"btn btn-danger\">CLICK TO ASSIGN MORE PATIENT</button></a></span>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "    ");
					

				}
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
