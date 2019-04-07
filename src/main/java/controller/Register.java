package controller;

import bean.UserAccount;
import dao.DbConnection;
import dao.UsersTable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class Register extends HttpServlet {
    public void doMethod (HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        String uid = req.getParameter("username");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phonenumber = req.getParameter("phonenumber");

        UserAccount account = new UserAccount();
        account.setUid(uid);
        account.setUsername(firstName + " " + lastName);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhonenumber(phonenumber);
        account.setAddress(address);
        account.setIsAdmin(2);

        Connection conn = DbConnection.getDbConnection("mysql");

        UsersTable usersTable = new UsersTable();
        usersTable.insertRecord(account, conn);



    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.doMethod(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.doMethod(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
