package com.nt.patientStatus;

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
import java.sql.Timestamp;

public class PatientServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String checkStatus="SELECT * FROM regPatient Where name=? AND regno=?";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String fname=req.getParameter("name");//not needed
		String fregno=req.getParameter("reg");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps=con.prepareStatement(checkStatus);
			ps.setString(1, fname);
			ps.setString(2, fregno);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String name=rs.getString("name");
				int age =Integer.parseInt(rs.getString("age"));
				String city=rs.getString("city");
				String gender=rs.getString("gender");
				String issue=rs.getString("issue");
				String roomno=rs.getString("roomno");
				String regno=rs.getString("regno");
				String time=rs.getString("time");
				pw.println(" <div style=\"height: 450px; width: 800px; padding: 20px; border: 2px solid gray; margin: 100px; background-color: aquamarine ; border-radius: 15px; box-shadow: 3px 3px 3px gray; font-size: 20px;\">\r\n"
						+ "        <h3 style=\"text-align: center; text-decoration: underline;\">PATIENT INFORMATION</h3>\r\n"
						+ "        <div style=\"\">\r\n"
						+ "            <div style=\"height: 60px; border: 2px solid black; padding: 15px;\">\r\n"
						+ "                <span >Name:-</span> <span class=\"text-danger\">"+name+"</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   <span >Age:-</span> <spanclass=\"text-danger\">"+age+"</span>\r\n"
						+ "                \r\n"
						+ "            </div>\r\n"
						+ "            <div style=\"height: 60px; border: 2px solid black; padding: 15px;\">\r\n"
						+ "                <span >City:-</span> <span class=\"text-danger\">"+city+"</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   <span >Gender:-</span> <spanclass=\"text-danger\">"+gender+"</span>\r\n"
						+ "                \r\n"
						+ "            </div>\r\n"
						+ "            <div style=\"height: 60px; border: 2px solid black; padding: 15px;\">\r\n"
						+ "                <span >Issue:-</span> <span class=\"text-danger\">"+issue+"</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   <span >Reg:-</span> <spanclass=\"text-danger\">"+regno+"</span>\r\n"
						+ "                \r\n"
						+ "            </div>\r\n"
						+ "            <div style=\"height: 60px; border: 2px solid black; padding: 15px;\">\r\n"
						+ "                <span >Room:-</span> <span class=\"text-danger\">"+roomno+"</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   <span >Time:-</span> <spanclass=\"text-danger\">"+time+"</span>\r\n"
						+ "                \r\n"
						+ "            </div>\r\n"
						+ "            \r\n"
						+ "            \r\n"
						+ "        </div>\r\n"
						+ "        \r\n"
						+ "    </div>");
				pw.println("<a href=\"CheckPatient.html\" style=\"text-decoration: none; margin: 50px; border: 1px solid red; background-color: red; color: white; padding: 5px;border-radius: 7px;\">PREV</a>\r\n"
						+ "");



				
			}
			
		}catch(Exception e) {
			
		}
				//generate a report using the data
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
