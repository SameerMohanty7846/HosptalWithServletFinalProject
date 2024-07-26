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
import java.sql.ResultSet;


public class PatientInfoInDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GETPATIENTDETAILS="select name,age,gender,issue from regpatient where regno=?";



	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		
		String reg=req.getParameter("regno");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps=con.prepareStatement(GETPATIENTDETAILS);
			ps.setString(1, reg);
			ResultSet rs=ps.executeQuery();
			HttpSession ses=req.getSession();
			String docName=(String) ses.getAttribute("doctor");
			while(rs.next()) {
				String name=rs.getString("name").toUpperCase();
				int age=rs.getInt("age");
				String gender=rs.getString("gender").toUpperCase();
				String issue=rs.getString("issue").toUpperCase();
				
				pw.println(" <div class=\"border border-dark w-75\">\r\n"
						+ "            <h3 class=\"p-1   bg-info \">PATIENT INFORMATION</h3>\r\n"
						+ "            <span class=\"p-2\">NAME:-</span><span class=\"p-2\">"+name+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">AGE:-</span><span class=\"p-2\">"+age+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">GENDER:-</span><span class=\"p-2\">"+gender+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">DISEASE:-</span><span class=\"p-2\">"+issue+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">DOCTOR NAME:-</span><span class=\"p-2\">"+docName+"</span> <br>\r\n"
						+ "\r\n"
						+ "            \r\n"
						+ "            \r\n"
						+ "\r\n"
						+ "        </div>\r\n"
						+ "        ");
			}
			HttpSession ses2=req.getSession();
			ses2.setAttribute("reg", reg);
			ses2.setAttribute("doc-doc", docName);

			

				pw.println("  <div class=\"border border-dark w-75\">\r\n"
						+ "            <h3 class=\"p-1  bg-info\">DOCTOR'S DIAGNOSIS</h3>\r\n"
						+ "\r\n"
						+ "            <form action="+res.encodeUrl("DiagnoseData")+" class=\"h-75 w-100 \">\r\n"
						+ "               \r\n"
						+ "                    <textarea name='diagnose' id=\"\" style=\"resize: none; height: 400px; width: 1000px; margin: 10px;\" ></textarea> <br>\r\n"
						+ "\r\n"
						+ "                \r\n"
						+ "                <button class=\"btn btn-info m-4\" type=\"submit\">PRINT</button>    <button class=\"btn btn-danger m-4\" type=\"reset\">DISCARD</button>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "            </form>\r\n"
						+ "           \r\n"
						+ "        </div>");
				
				
				
				
		
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
