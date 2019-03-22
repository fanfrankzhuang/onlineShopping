package dao;

import bean.UserAccount;

import java.sql.Connection;
import java.sql.SQLException;

public class testConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*DbConnection connection = new DbConnection();
        connection.getDbConnection("mysql");*/
        Connection conn = DbConnection.getDbConnection("mysql");
        /*UserAccount account = new UserAccount();
        account.setUid("fzhuang");
        //account.setUsername("fzhuang");
        account.setPassword("654321");
        //account.setType(1);
        //account.setPhonenumber("1234567890");
        //account.setEmail("fzhuang@xxx.com");
       // account.setAddress("djflsajfiejllsk");*/
        UsersTable usersTable = new UsersTable();
        usersTable.getRecord("fzhuang",conn);



    }
}
