
package basic.atm;

import java.sql.*;

public class Operation {
    private String cardNumber;
    
    private String pincode;
    
    private Integer balance;
    
    Operation(String cardNumber, String pincode) {
        this.cardNumber = cardNumber;
        this.pincode = pincode;
    }
    
    /*
    Displaying the balance of the bank card
    */
    public Integer showBalance(String cardNumber) {
        try {
            Connection c = Database.connection();
            
            Statement stmt5 = c.createStatement();
            
            String sql5 = "SELECT * FROM balance WHERE card_number = " + cardNumber;
            
            ResultSet rs5 = stmt5.executeQuery(sql5);
            
            while (rs5.next()) {
                this.balance = rs5.getInt(2);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return this.balance;
    }
    
    
    /*
    Put money on a bank card
    */
    public void deposit(Integer amount, String cardNumber) {
        try {
            Connection c = Database.connection();
            
            Statement stmt6 = c.createStatement();
            
            String sql6 = "UPDATE balance SET balance = balance + '" + amount + "' WHERE card_number = " + cardNumber;
            
            stmt6.executeUpdate(sql6);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /*
    Transfer money to another user
    */
    public void sendMoneyToOther(Integer amountOther, String numberOther, String cardNumber) {
        try {
            Connection c = Database.connection();
            
            Statement stmt8 = c.createStatement();
            
            String sql8 = "UPDATE balance SET balance = balance + " + amountOther + " WHERE card_number = '" + numberOther + "'";
            
            stmt8.executeUpdate(sql8);
            
            
            Statement stmt9 = c.createStatement();
            
            String sql9 = "UPDATE balance SET balance = balance - " + amountOther + " WHERE card_number = '" + cardNumber + "'";
            
            stmt9.executeUpdate(sql9);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
