package com.nt.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		HttpSession ses = req.getSession();
		
		Integer Bill =(Integer) ses.getAttribute("bill");
		String regno = (String) ses.getAttribute("regno");
		String paymentMethod = req.getParameter("payment");
		int bill=Bill;
		if (paymentMethod.equalsIgnoreCase("cash")) {
			pw.println(
					" <div style=\"height: 350px; width: 650px; padding: 20px; border: 2px solid gray; margin: 100px; background-color: aquamarine ; border-radius: 15px; box-shadow: 3px 3px 3px gray; font-size: 50px;\">\r\n"
							+ "        <span>PLEASE PAY</span> <span style=\"font-size: 60px; font-weight: bold; text-decoration: underline;\">"+bill+"/-</span>  <span>AND TAKE YOUR MEDICINES</span>\r\n"
							+ "        <form action=\"PaymentServlet2\">\r\n"
							+ "            <input type=\"hidden\" name=\"bill\" value='" + bill + "'>\r\n"
							+ "            <input type=\"hidden\" name=\"method\" value=\"cash\">\r\n"
							+ "            <input type=\"hidden\" name=\"reg\" value='" + regno + "'>\r\n"
							+ "            <button class=\"btn btn-success w-50\" type='submit'>PAID</button>\r\n" + "\r\n"
							+ "        </form>\r\n" + "        \r\n" + "    </div>\r\n" + "   ");

		} else if (paymentMethod.equalsIgnoreCase("upi")) {
			pw.println(" <div style=\"height: 350px; width: 650px; padding: 20px; border: 2px solid gray; margin: 100px; background-color: aquamarine ; border-radius: 15px; box-shadow: 3px 3px 3px gray; font-size: 50px;\">\r\n"
					+ "        <img src=\"WhatsApp Image 2024-07-25 at 2.59.55 PM.jpeg\" alt=\"\" style=\"height: 250px; width: 250px;margin-left: 150px;\">\r\n"
					+ "        \r\n"
					+ "        <form action=\"PaymentServlet2\">\r\n"
					+ "            <input type=\"hidden\" name=\"bill\" value='"+bill+"'>\r\n"
					+ "            <input type=\"hidden\" name=\"method\" value=\"upi\">\r\n"
					+ "            <input type=\"hidden\" name=\"reg\" value='"+regno+"'>\r\n"
					+ "            <button class=\"btn btn-success w-50 \" style=\"margin-left: 120px;\">PAID</button>\r\n"
					+ "\r\n"
					+ "        </form>\r\n"
					+ "        \r\n"
					+ "    </div>");
			
		} else if (paymentMethod.equalsIgnoreCase("credit")) {
			pw.println(" <div style=\"height: 350px; width: 650px; padding: 20px; border: 2px solid gray; margin: 100px; background-color: aquamarine ; border-radius: 15px; box-shadow: 3px 3px 3px gray; font-size: 50px;\">\r\n"
					+ "        <form action=\"PaymentServlet2\">\r\n"
					+ "            <input type=\"hidden\" name=\"bill\" value='"+bill+"'>\r\n"
					+ "            <input type=\"hidden\" name=\"method\" value=\"credit\">\r\n"
					+ "            <input type=\"hidden\" name=\"reg\" value='"+regno+"'>\r\n"
					+ "            <button class=\"btn btn-success w-75 \" style=\"margin-left: 75px;margin-top: 150px;\">PAID</button>\r\n"
					+ "\r\n"
					+ "        </form>\r\n"
					+ "        \r\n"
					+ "    </div>");
			
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
