package controller;

import bean.UserAccount;
import dao.AccountsDAO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Register extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) throws  IOException {
        try {
            String username = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            UserAccount account = new UserAccount();
            account.setUsername(username);
            account.setPassword(password);
            account.setEmail(email);
            account.setIsAdmin(0);

            AccountsDAO accountsDAO = new AccountsDAO();
            int result = accountsDAO.insertUser(account);
            if (result == 0) {
                Cookie cookie = new Cookie("uid", username);
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("error.html");
            }
        } catch (Exception e) {
            e.getStackTrace();
            response.sendRedirect("error.html");
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.doMethod(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.doMethod(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
