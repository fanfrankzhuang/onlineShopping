package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getDbConnection(String dbName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        if (dbName.equalsIgnoreCase("mysql")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlineshopping?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "fzhuang", "woshi_ZF!2");

            return conn;
        } else {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "system","1234");

            return conn;
        }

    }
}
