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

public class BillingServlet2 extends HttpServlet {
	private static final String query="SELECT price from medicine where medicinename=?";
	private static final String GETPATIENTINFO="select name,age,gender,issue from regpatient where regno=?";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css'>");

		pw.println(
				"<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css'>");
		HttpSession ses = req.getSession();
		String reg = (String) ses.getAttribute("regno");
		String m1 = req.getParameter("medicine-1");
		String m2 = req.getParameter("medicine-2");
		String m3 = req.getParameter("medicine-3");
		String m4 = req.getParameter("medicine-4");
		String m5 = req.getParameter("medicine-5");

		String q1 = req.getParameter("qty-1");
		String q2 = req.getParameter("qty-2");
		String q3 = req.getParameter("qty-3");
		String q4 = req.getParameter("qty-4");
		String q5 = req.getParameter("qty-5");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			System.out.println("Connection Created");
			PreparedStatement ps1=con.prepareStatement(GETPATIENTINFO);
			ps1.setString(1,reg);
			ResultSet rs1=ps1.executeQuery();
			
			while(rs1.next()){
				String name=rs1.getString("name");
				int age=rs1.getInt("age");
				String gender=rs1.getString("gender");
				String issue=rs1.getString("issue");
				pw.println("<div class=\"border border-dark w-75\">\r\n"
						+ "            <h3 class=\"p-1   bg-info \">PATIENT INFORMATION</h3>\r\n"
						+ "            <span class=\"p-2\">NAME:-</span><span class=\"p-2\">"+name.toUpperCase()+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">AGE:-</span><span class=\"p-2\">"+age+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">GENDER:-</span><span class=\"p-2\">"+gender.toUpperCase()+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">DISEASE:-</span><span class=\"p-2\">"+issue+"</span> <br>\r\n"
						+ "            <span class=\"p-2\">REGISTRATION NUMBER:-</span><span class=\"p-2\">"+reg+"</span> <br>\r\n"
						+ "\r\n"
						+ "            \r\n"
						+ "            \r\n"
						+ "\r\n"
						+ "        </div>");
				
				
			}

			String arr1[] = { m1, m2, m3, m4, m5 };
			String arr2[] = { q1, q2, q3, q4, q5 };

			int i = 0;int finalBill=0;
			pw.println(" <h3 class=\"p-1 mt-4 bg-info w-75\">BILLING</h3>\r\n"
					+ "            <table class=\"border border-3 border-dark w-75 \">\r\n"
					+ "                <tr class=\"\">\r\n"
					+ "                    <th class=\"p-3\">MEDICINE</th>\r\n"
					+ "                    <th class=\"p-3\">QUANTITY</th>\r\n"
					+ "                    <th class=\"p-3\">PRICE</th>\r\n"
					+ "                    <th class=\"p-3\">TOTAL</th>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "                </tr>");
			while (i < 5) {
				if (arr1[i] != null && arr2[i]!=null) {
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1,arr1[i]);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						int qty=Integer.parseInt(arr2[i]);
						int price=rs.getInt("price");
						int x=qty*price;
						pw.println("<tr>\r\n"
								+ "<td class=\"p-3\">"+arr1[i]+"</td>\r\n"
								+ "<td class=\"p-3\">"+qty+"</td>\r\n"
								+ "<td class=\"p-3\">"+price+"</td>\r\n"
								+ "<td class=\"p-3\">"+x+"</td>\r\n"
								+ "\r\n"
								+ "\r\n"
								+ "                </tr>");
						finalBill=finalBill+x;
						
						
					}


				}
				i++;

			}
			int docFee=500;
			int medicine=finalBill;
			int finalAmount=500+finalBill;
			pw.println(" </table>\r\n"
					+ "            <br>\r\n"
					+ "            <br><br>\r\n"
					+ "            <span style=\"font-size: larger; margin-left: 500px; text-decoration: underline;\"><b>DOCTOR'S FEE="+docFee+"+MEDICINE="+medicine+" TOTAL BILL:-</b></span> <span style=\"text-decoration: underline;font-size: larger; \"><b>"+finalAmount+"</b></span>\r\n"
					+ "\r\n"
					+ "            \r\n"
					+ "           \r\n"
					+ "    ");
			HttpSession ses2=req.getSession();
			ses2.setAttribute("regno", reg);
			ses2.setAttribute("bill", finalAmount);
			
			pw.println(" <div style=\"height: 120px; width: 350px; padding: 20px; border: 2px solid gray; margin: 50px; background-color: aquamarine ; border-radius: 15px; box-shadow: 3px 3px 3px gray;\">\r\n"
					+ "        <form action="+res.encodeUrl("PaymentServlet")+" method=\"post\">\r\n"
					+ "            <label for=\"payment\">Choose The Method Of Payment</label>\r\n"
					+ "            <div>\r\n"
					+ "                <input type=\"radio\" name=\"payment\" value='cash' class=\"form-check-input\">CASH\r\n"
					+ "                <input type=\"radio\" name=\"payment\" value='upi' class=\"form-check-input\">UPI\r\n"
					+ "                <input type=\"radio\" name=\"payment\" value='credit' class=\"form-check-input\">CREDIT\r\n"
					+ "\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "            <div>\r\n"
					+ "                <button class=\"btn btn-warning mt-2\" type=\"submit\">continue</button>\r\n"
					+ "            </div>\r\n"
					+ "        </form>\r\n"
					+ "    </div>");

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
