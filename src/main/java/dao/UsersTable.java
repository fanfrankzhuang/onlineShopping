package dao;

import bean.UserAccount;

import java.sql.*;

public class UsersTable {
    public void insertRecord(UserAccount account, Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into accounts values (?,?,?,?,?,?,?)");
            stmt.setString(1, account.getUid());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getPassword());
            stmt.setInt(4, account.getType());
            stmt.setString(5, account.getPhonenumber());
            stmt.setString(6, account.getEmail());
            stmt.setString(7, account.getAddress());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateRecord(UserAccount account, Connection conn) {
        PreparedStatement stmt = null;
        try {
            String sql = "update accounts set uid = uid";
            if (account.getUsername() != null) {
                sql += ", name = '" + account.getUsername() + "'";
            }
            if (account.getAddress() != null) {
                sql += ", address = '" + account.getAddress() + "'";
            }
            if (account.getPhonenumber() != null) {
                sql += ", phonenumber = '" + account.getPhonenumber() + "'";
            }
            if (account.getEmail() != null) {
                sql += ", email = '" + account.getEmail() + "'";
            }
            if (account.getPassword() != null) {
                sql += ", password = '" + account.getPassword() + "'";
            }
            sql += " where uid = '" + account.getUid() + "';";
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteRecord(String uid, Connection conn) {
        Statement stmt = null;
        try {
            String sql = "delete from accounts where uid = '" + uid + "';";
            stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getRecord(String uid, Connection conn) {
        Statement stmt = null;
        try {
            String sql = "select * from accounts where uid = '" + uid + "';";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
