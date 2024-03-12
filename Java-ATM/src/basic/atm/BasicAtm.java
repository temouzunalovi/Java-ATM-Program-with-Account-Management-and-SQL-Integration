
package basic.atm;

import java.util.Scanner;
import java.sql.*;

public class BasicAtm {
    
    public static boolean isLogin = false;

    public static void main(String[] args) {

        Integer option = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        //Displaying the main menu
        while (option == 0) {
            System.out.println("==== SELECT AN OPTION ====");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in\n");
            
            while (option < 1 || option > 2) {
                System.out.println("Type your choice: ");
                
                option = scanner.nextInt();
            }
        }
        
        //Data entry depending on the selected option
        switch(option) {
            case 1: //Creating a new account
                System.out.println("\n\n=== CREATE NEW ACCOUNT ===\n");
                
                System.out.println("Enter first name:");
                
                String firstName = scanner.next().trim();
                
                System.out.println("Enter last name:");
                
                String lastName = scanner.next().trim();
                
                
                Account account = new Account(firstName, lastName);
                
                account.register();
                
                break;
            
            case 2: //Login to an existing account
                System.out.println("\n\n==== SIGN IN ====\n");
                
                System.out.println("Enter your card number: ");
                
                String cardNumber = scanner.next();
                
                System.out.println("Enter your pincode: ");
                
                String pincode = scanner.next();
                
                Operation operation = new Operation(cardNumber, pincode);
                
                //Checking that the user exists
                try {
                    Connection c = Database.connection();
                    
                    Statement stmt4 = c.createStatement();
                    
                    String sql4 = "SELECT * FROM card WHERE card_number = '" + cardNumber + "' AND pincode = '" + pincode + "'";
                    
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    
                    //Display the menu of the user who is logged in
                    if (rs4.next()) {
                        isLogin = true;
                        
                        System.out.println("\n\n==== LOGIN SUCCESS ====\n");
                        
                        System.out.println("--- Enter an option ---");
                        
                        System.out.println("1. Balance");
                        System.out.println("2. Deposit");
                        System.out.println("3. Send to other person");
                        
                        Integer option_user = 0;
                        
                        while (option_user < 1 || option_user > 3) {
                            System.out.println("\nType your choice: ");
                            
                            option_user = scanner.nextInt();
                        }
                        
                        Integer balance = 0;
                        
                        //Data entry depending on the selected option
                        switch(option_user) {
                            case 1:
                                System.out.println("\n\n==== SHOW BALANCE ====\n");
                                
                                balance = operation.showBalance(cardNumber);
                                
                                System.out.println(balance);
                                
                                break;
                            case 2:
                                System.out.println("\n\n==== MAKE DEPOSIT ====\n");
                                
                                Integer amount = 0;
                                
                                while (amount <= 0) {
                                    System.out.println("Type amount: ");
                                    
                                    amount = scanner.nextInt();
                                }
                                
                                operation.deposit(amount, cardNumber);
                                
                                balance = operation.showBalance(cardNumber);
                                
                                System.out.println("\nCurrent balance is " + balance);
                                
                                break;
                                
                            case 3:
                                System.out.println("\n\n==== SEND MONEY TO OTHER CARD ====\n");
                                
                                System.out.println("Enter number of other client: ");
                                
                                String number_other = scanner.next();
                                
                                Integer amountOther = 0;
                                
                                while (amountOther <= 0) {
                                    System.out.println("Enter amount for other client: ");
                                    
                                    amountOther = scanner.nextInt();
                                }
                                
                                operation.sendMoneyToOther(amountOther, number_other, cardNumber);
                                
                                System.out.println("\nYou've sent " + amountOther + " to " + number_other);
                                
                                break;
                            
                            default:
                                break;
                        }
                        
                    } else {
                        System.out.println("\nLogin fail");
                    }
                } catch (Exception e) {
                    
                }
                break;
                
            default:
               break;
            
            
        }
    }
    
}
