package controller;

import dao.AccountsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet{
	public void doMethod(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		AccountsDAO accountsDAO = new AccountsDAO();
		try {
			int result = accountsDAO.checkLogin(username, password);
			request.setAttribute("resultCode", result);
			if (result == 0) {
				response.sendRedirect("index.jsp");
			} else {
				/*PrintWriter out = response.getWriter();
				out.println("Username doesn't exist! Please sign up first.");*/
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*if(username.equalsIgnoreCase("java")&& password.equalsIgnoreCase("jee"))
		{
			PrintWriter pw = response.getWriter();
			String res ="<HTML>";
			res += "Success " + "UserName: " + username + "<br/>" + "Password: " + password;
			res += "</HTML>";
			pw.println(res);
		}
		else
		{
			response.sendRedirect("error.html");
		}*/
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		doMethod(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		doMethod(req, res);
	}
}
	
