package com.nt.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


public class PharmacyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		HttpSession ses=req.getSession();
		String name=(String)ses.getAttribute("p-name");
		String pass=(String)ses.getAttribute("p-pass");
		ses.setAttribute("doctor", name);
		pw.println("<div class=\"d-flex justify-centre border border-4 border-success\">\r\n"
				+ "    <div class=\"border border-4 w-50\">\r\n"
				+ "            <img src=\"User-Profile-PNG-Image.png\" height=\"500\" width=\"500\">\r\n"
				+ "      <div >\r\n"
				+ "        <span class=\" ps-3\">USERNAME:-</span> <span class=\"  p-1\"><b>"+name+"</b></span>         <span class=\" ps-3\">PASSWORD:-</span> <span class=\" p-1\"><b>"+pass+"</b></span>     <span class=\"\"><a href=\"index.html\"><button class=\"btn btn-danger\">LOGOUT</button></a></span>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "      </div>\r\n"
				+ "\r\n"
				+ "    </div>\r\n"
				+"  <div class=\"p-4 m-4 border border-dark w-50\" >\r\n"
				+ "        <h5>BILLING FOR A PATIENT</h5>\r\n"
				+ "        <form action=\"BillingServlet\">\r\n"
				+ "            <input type=\"text\" name=\"regno\" placeholder=\"enter registration number\" class=\"form-control w-75\" autofocus><button type=\"submit\" class=\"btn btn-warning mt-3\">ENTER</button>\r\n"
				+ "        </form>\r\n"
				+ "    </div>\r\n"
				+ "    "
				

				+ "  </div>"
				+ "");



	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
