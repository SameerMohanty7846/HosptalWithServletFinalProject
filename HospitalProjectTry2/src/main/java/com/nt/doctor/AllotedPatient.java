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


public class AllotedPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GETREGNO="SELECT regno from doctorassignment where doc=?";


	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		pw.println(" <style>\r\n"
				+ "         body{\r\n"
				+ "            background-color: rgb(3, 86, 112);\r\n"
				+ "            background-repeat: no-repeat;\r\n"
				+ "        }\r\n"
				+ "       \r\n"
				+ "        .container-1{\r\n"
				+ "            border: 1px white solid;\r\n"
				+ "            padding: 50px;\r\n"
				+ "            margin: 40px;\r\n"
				+ "            width: 600px;\r\n"
				+ "        }\r\n"
				+ "        .container-2{\r\n"
				+ "            border: 1px white solid;\r\n"
				+ "            margin: 40px;\r\n"
				+ "            margin-left: 680px;\r\n"
				+ "            height: 300px;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            width: 600px;\r\n"
				+ "\r\n"
				+ "            \r\n"
				+ "        }\r\n"
				+ "       \r\n"
				+ "    </style>");

		HttpSession ses=req.getSession();
		String name=(String)ses.getAttribute("doctor");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");

			PreparedStatement ps=con.prepareStatement(GETREGNO);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			pw.println("<body >\r\n"
					+ "    <div class=\"maindiv\">\r\n"
					+ "        <div class=\"container-1\">\r\n"
					+ "            <table class=\"p-2 m-3 border border-dark w-50\">\r\n"
					+ "                <tr class=\"p-2 m-3 border border-dark \">\r\n"
					+ "                    <th class=\"p-2 m-3 border border-dark   bg-warning\">REGISTRATION NUMBER</th>\r\n"
					+ "                </tr>");
			while(rs.next()) {
				String reg=rs.getString("regno");
				if(reg!=null) {
					pw.println(" <tr>\r\n"
							+ "                    <td class=\"p-2 m-3 border border-dark  bg-success text-white\">"+reg+"</td>\r\n"
							+ "                </tr>");
					
				}else {
					pw.println(" <tr>\r\n"
							+ "                    <td class=\"p-2 m-3 border border-dark  bg-success text-white\">NO PATIENT ASSIGNE</td>\r\n"
							+ "                </tr>");
					
				}
				
				
			}
			pw.println("  </table>\r\n"
					+ "\r\n"
					+ "        </div>\r\n"
					+ "\r\n"
					+ "        \r\n"
					+ "        <div class=\"container-2\">\r\n"
					+ "            <form action="+res.encodeUrl("PatientInfoInDetails")+">\r\n"
					+ "                <p class=\"bg-dark text-white p-4 m-4 input-group-text\" >GET DETAILED INFO OF PATIENT BY REGISTRATION NUMBER</p>\r\n"
					+ "                <div class=\"input-group border-3 border-warning\">\r\n"
					+ "                    <input type=\"text\" name=\"regno\" class=\"form-control w-50\" autofocus> <button> &nbsp;<span class=\"bi bi-search\"></span></button>\r\n"
					+ "\r\n"
					+ "                </div>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "            </form>\r\n"
					+ "\r\n"
					+ "        </div>\r\n"
					+ "\r\n"
					+ "    </div>\r\n"
					+ "\r\n <br> <br> <br> <br>"
					+ "    <span class=\"\"><a href='DoctorServlet2'><button class=\"btn btn-danger\">PROFILE</button></a></span>\r\n"
					+ ""
					+ "</body>");
			
			
		}catch(Exception e) {
			e.printStackTrace();		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
