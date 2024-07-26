package com.nt.assignPatient;

import jakarta.servlet.RequestDispatcher;
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
import java.sql.Timestamp;


public class AssignPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String viewAllPatient =" select name ,regno from patientforallocation";
	private static final String getDoc ="SELECT user from doctor";


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");

		PrintWriter pw=res.getWriter();
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");

			PreparedStatement ps=con.prepareStatement(viewAllPatient);
			ResultSet rs=ps.executeQuery();
			pw.println("  <div class=\"assign\">\r\n"
					+ "");

			
			pw.println("<section style=\"display: flex;\">\r\n"
					+ "        <div>\r\n"
					+ "            <table style=\"border: 3px red solid; height: 650px; width: 500px; margin: 50px;\">\r\n"
					+ "                <tr style=\"border: 2px red solid; text-align: center; background-color: yellow;\">\r\n"
					+ "                    <th>REGNO</th>\r\n"
					+ "                    <th>NAME</th>\r\n"
					+ "                </tr>");
			while(rs.next()) {
				String name=rs.getString("name").trim();
				String regno=rs.getString("regno").trim();
				pw.println("  <tr style=\"border: 2px red solid ;text-align: center; background-color: green; color: whitesmoke;\">\r\n"
						+ "                    <td>"+regno+"</td>\r\n"
						+ "                    <td>"+name+"</td>\r\n"
						+ "                </tr>");

				
			}
			pw.println("   </table>\r\n"
					+ "        </div>");
			pw.println(" <div>\r\n"
					+ "            <form action=\"AssignPatient2\" style=\"width: 500px; border-radius: 50px;   padding: 40px; position: fixed; margin-top: 50px; margin-left: 200px;\" class=\"bg-info border border-3 \">\r\n"
					+ "                <div>\r\n"
					+ "                    <label for=\"regno\" class=\"form-label\">Enter Registration Number</label>\r\n"
					+ "                    <div>\r\n"
					+ "                        <input type=\"text\" name=\"regno\" class=\"form-control\" autofocus required >\r\n"
					+ "                    </div>\r\n"
					+ "\r\n"
					+ "                </div>\r\n"
					+ "                <div>\r\n"
					+ "                    <label for=\"doc\" class=\"form-label\">Select Doctor</label>\r\n"
					+ "                    <div>\r\n"
					+ "                        <select name=\"doc\" id=\"\" class=\"form-select\">");
			
			
			PreparedStatement ps2=con.prepareStatement(getDoc);
			ResultSet r=ps2.executeQuery();
			
			while(r.next()) {
				String docName=r.getString("user").toUpperCase();
				pw.println("<option  class='form-check-input' value='"+docName+"'>"+docName+"</option>\r\n");
			}
			pw.println(" </select>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div>\r\n"
					+ "                        <button class=\"btn btn-warning mt-4 me-2\" type=\"submit\">ASSIGN</button> <button class=\"btn btn-danger mt-4 me-2\" type=\"reset\">DISCARD</button><br>\r\n"
					+ "                        <a href=\"Servlet2\" class=\"btn btn-danger mt-4 \">HOME </a>\r\n"
					+ " \r\n"
					+ "                    </div>\r\n"
					+ "\r\n"
					+ "                </div>\r\n"
					+ "            </form>\r\n"
					+ "          \r\n"
					+ "            \r\n"
					+ "        </div>\r\n"
					+ "\r\n"
					+ "    </section>\r\n"
					+ "   ");
			
			
			
					
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd=req.getRequestDispatcher("/ErrorServlet");
			rd.forward(req, res);
		}
		
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
