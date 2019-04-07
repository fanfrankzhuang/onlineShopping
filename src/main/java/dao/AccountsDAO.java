package dao;

import bean.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountsDAO {
    public int checkLogin (String username, String password) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement = null;
        try {
            String sql = "select username,password from user where username = '" + username + "';";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                //password not correct
                if (!resultSet.getString("password").equals(password)) {
                    result = -2;
                }
            } else {
                //User doesn't exist
                result = -1;
            }
        } catch (Exception e) {
            result = -3;
            e.getStackTrace();
        }
        return result;
    }

    public UserAccount getUser(String uid) throws SQLException, ClassNotFoundException {
        UserAccount user = new UserAccount();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement = null;
        try {
            String sql = "select uid,username,password,email, phone,isAdmin,address from user where username = '" + uid + "';";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                user.setUid(resultSet.getString("uid"));
                user.setUsername(resultSet.getString("username"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                user.setAddress(resultSet.getString("address"));
                user.setPhonenumber(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return user;
    }
}
