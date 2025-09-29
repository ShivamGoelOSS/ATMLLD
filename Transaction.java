import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private TransactionType type;
    private double amount;
    private String cardNumber;
    private LocalDateTime timestamp;
    
    public Transaction(TransactionType type, double amount, String cardNumber) {
        this.transactionId = generateTransactionId();
        this.type = type;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.timestamp = LocalDateTime.now();
    }
    
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return String.format("Transaction[id=%s, type=%s, amount=%.2f, timestamp=%s]", 
                transactionId, type, amount, timestamp);
    }
}