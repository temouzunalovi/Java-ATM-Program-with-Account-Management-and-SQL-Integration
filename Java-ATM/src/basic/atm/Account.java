
package basic.atm;

import java.sql.*;
public class Account {
    
    private String firstName;
    
    private String lastName;
    
    private Integer balance;
    
    Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /*
    Registration of new user
    */
    public Boolean register() {
        try {
            Connection c = Database.connection();
            
            Statement stmt = c.createStatement();
            
            String sql = "INSERT INTO account VALUES (null, '" + this.firstName + "', '" + this.lastName + "')";
            
            stmt.executeUpdate(sql);
            
            
            Statement stmt2 = c.createStatement();
            String sql2 = "SELECT LAST_INSERT_ID()";
            ResultSet rs2 = stmt.executeQuery(sql2);
            
            Integer last_account_id = 0;
            
            while (rs2.next()) {
                last_account_id = rs2.getInt(1);
            }
            
            String cardNumber = this.generateCardNumber();
            
            String code = this.generatePincode();
            
            Statement stmt3 = c.createStatement();
            
            String sql3 = "INSERT INTO card VALUES ('" + last_account_id + "', '" + cardNumber + "', '" + code + "')";
            
            stmt3.executeUpdate(sql3);
            
            
            Statement stmt4 = c.createStatement();
            
            String sql4 = "INSERT INTO balance VALUES ('" + cardNumber + "', '0')";
            
            stmt4.executeUpdate(sql4);
            
            
            c.close();
            
            
            System.out.println("Account created successfully");
            
            System.out.println("Card number: " + cardNumber);
            
            System.out.println("Pincode: " + code);
            
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
    
    /*
    Card number generation
    */
    public String generateCardNumber() {
        Integer length = 8;
        
        String passwordSet = "1234567890";
        
        char[] cardNumber = new char[length];
        
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            
            cardNumber[i] = passwordSet.charAt(rand);
        }
        
        return new String(cardNumber);
    }
    
    /*
    Pin code generation
    */
    public String generatePincode() {
        Integer length = 4;
        
        String passwordSet = "1234567890";
        
        char[] cardNumber = new char[length];
        
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            
            cardNumber[i] = passwordSet.charAt(rand);
        }
        
        return new String(cardNumber);
    }
        
        
}
