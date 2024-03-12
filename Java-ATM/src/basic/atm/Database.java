
package basic.atm;

import java.sql.*;

public class Database {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/java_basic_atm";
    
    private static final String USER = "opencart_user";
    
    private static final String PASSWORD = "password";
    
    public static java.sql.Connection connection() throws Exception {
        Class.forName(dbClassName);
        
        return DriverManager.getConnection(CONNECTION, USER, PASSWORD);
    }
}
