package dao;

import bean.UserAccount;

import java.sql.*;

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

    public int insertUser(UserAccount userAccount) throws SQLException, ClassNotFoundException {
        Connection conn = DbConnection.getDbConnection("mysql");
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("insert into user (username, password, email, isadmin) " +
                    " values (?,?,?,?)");
            stmt.setString(1, userAccount.getUsername());
            stmt.setString(2, userAccount.getPassword());
            stmt.setString(3, userAccount.getEmail());
            stmt.setInt(4, userAccount.getIsAdmin());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.getStackTrace();
            return -1;

        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
