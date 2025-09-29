import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<String, Double> accountBalances;
    private Map<String, List<Transaction>> transactionHistory;
    
    public BankService() {
        accountBalances = new HashMap<>();
        transactionHistory = new HashMap<>();
        
        // Initialize with some test data
        accountBalances.put("1234567890123456", 10000.0);
        transactionHistory.put("1234567890123456", new ArrayList<>());
    }
    
    public boolean validateBalance(String cardNumber, double amount) {
        if (!accountBalances.containsKey(cardNumber)) {
            return false;
        }
        return accountBalances.get(cardNumber) >= amount;
    }
    
    public void processTransaction(Transaction transaction) {
        String cardNumber = transaction.getCardNumber();
        
        if (!accountBalances.containsKey(cardNumber)) {
            throw new IllegalArgumentException("Card not registered with bank");
        }
        
        double currentBalance = accountBalances.get(cardNumber);
        
        switch (transaction.getType()) {
            case WITHDRAWAL:
                if (currentBalance >= transaction.getAmount()) {
                    accountBalances.put(cardNumber, currentBalance - transaction.getAmount());
                } else {
                    throw new IllegalStateException("Insufficient funds");
                }
                break;
            case DEPOSIT:
                accountBalances.put(cardNumber, currentBalance + transaction.getAmount());
                break;
            default:
                // No balance change for other transaction types
                break;
        }
        
        // Record transaction in history
        if (!transactionHistory.containsKey(cardNumber)) {
            transactionHistory.put(cardNumber, new ArrayList<>());
        }
        transactionHistory.get(cardNumber).add(transaction);
    }
    
    public double getBalance(String cardNumber) {
        if (!accountBalances.containsKey(cardNumber)) {
            throw new IllegalArgumentException("Card not registered with bank");
        }
        return accountBalances.get(cardNumber);
    }
    
    public List<Transaction> getTransactionHistory(String cardNumber) {
        if (!transactionHistory.containsKey(cardNumber)) {
            return new ArrayList<>();
        }
        
        List<Transaction> history = transactionHistory.get(cardNumber);
        // Return last 10 transactions or fewer if not available
        int startIndex = Math.max(0, history.size() - 10);
        return history.subList(startIndex, history.size());
    }
}