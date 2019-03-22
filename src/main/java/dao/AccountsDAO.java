package dao;

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
            String sql = "select uid,password from accounts where uid = '" + username + "';";
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
}
