import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Initialize ATM
        ATM atm = new ATM("ATM001", "Main Street Branch");
        Scanner scanner = new Scanner(System.in);
        
        // Create a test card
        Card card = new Card("1234567890123456", "John Doe", "12/25", 123, "1234");
        
        System.out.println("Welcome to the ATM System");
        System.out.println("Inserting card...");
        
        // Insert card
        atm.cardReader.readCard(card);
        
        // Authenticate
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        if (!atm.authenticateUser(pin)) {
            System.out.println("Invalid PIN. Card ejected.");
            atm.endSession();
            return;
        }
        
        System.out.println("Authentication successful!");
        
        boolean exit = false;
        while (!exit) {
            // Display menu
            atm.screen.displayMainMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1": // Check Balance
                    double balance = atm.bankService.getBalance(card.getCardNumber());
                    System.out.println("Current balance: Rs. " + balance);
                    break;
                    
                case "2": // Withdraw Cash
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (atm.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful");
                    } else {
                        System.out.println("Withdrawal failed");
                    }
                    break;
                    
                case "3": // Deposit Cash
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    if (atm.deposit(depositAmount)) {
                        System.out.println("Deposit successful");
                    } else {
                        System.out.println("Deposit failed");
                    }
                    break;
                    
                case "4": // Mini Statement
                    List<Transaction> miniStatement = atm.getMiniStatement();
                    System.out.println("\n===== MINI STATEMENT =====");
                    if (miniStatement.isEmpty()) {
                        System.out.println("No transactions found");
                    } else {
                        for (Transaction t : miniStatement) {
                            System.out.println(t);
                        }
                    }
                    System.out.println("=========================\n");
                    break;
                    
                case "5": // Change PIN
                    System.out.print("Enter current PIN: ");
                    String currentPin = scanner.nextLine();
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.nextLine();
                    
                    if (atm.changePin(currentPin, newPin)) {
                        System.out.println("PIN changed successfully");
                    } else {
                        System.out.println("Failed to change PIN");
                    }
                    break;
                    
                case "6": // Exit
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        
        // End session
        atm.endSession();
        System.out.println("Thank you for using the ATM. Goodbye!");
        scanner.close();
    }
}