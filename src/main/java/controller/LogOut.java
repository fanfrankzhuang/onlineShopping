package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogOut extends HttpServlet{
	public void doMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie=new Cookie("uid","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect("index.jsp");

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
	
