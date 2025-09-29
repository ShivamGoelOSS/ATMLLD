public class Screen {
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayMainMenu() {
        System.out.println("\n===== ATM MENU =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Mini Statement");
        System.out.println("5. Change PIN");
        System.out.println("6. Exit");
        System.out.println("====================");
    }
}