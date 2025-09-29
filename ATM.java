import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ATM {
    private String atmId;
    private String location;
    private Card currentCard;
    public BankService bankService;
    public CardReader cardReader;
    public CashDispenser cashDispenser;
    public ReceiptPrinter receiptPrinter;
    public PinPad pinPad;
    public Screen screen;
    private boolean userAuthenticated;

    public ATM(String atmId, String location) {
        this.atmId = atmId;
        this.location = location;
        this.bankService = new BankService();
        this.cardReader = new CardReader(this);
        this.cashDispenser = new CashDispenser();
        this.receiptPrinter = new ReceiptPrinter();
        this.pinPad = new PinPad();
        this.screen = new Screen();
        this.userAuthenticated = false;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public boolean authenticateUser(String pin) {
        if (currentCard != null && currentCard.validatePin(pin)) {
            userAuthenticated = true;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (!userAuthenticated || currentCard == null) {
            return false;
        }

        if (bankService.validateBalance(currentCard.getCardNumber(), amount)) {
            if (cashDispenser.dispenseCash(amount)) {
                Transaction transaction = new Transaction(
                    TransactionType.WITHDRAWAL,
                    amount,
                    currentCard.getCardNumber()
                );
                bankService.processTransaction(transaction);
                receiptPrinter.printReceipt(transaction);
                return true;
            }
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (!userAuthenticated || currentCard == null) {
            return false;
        }

        Transaction transaction = new Transaction(
            TransactionType.DEPOSIT,
            amount,
            currentCard.getCardNumber()
        );
        bankService.processTransaction(transaction);
        receiptPrinter.printReceipt(transaction);
        return true;
    }

    public List<Transaction> getMiniStatement() {
        if (!userAuthenticated || currentCard == null) {
            return new ArrayList<>();
        }
        return bankService.getTransactionHistory(currentCard.getCardNumber());
    }

    public boolean changePin(String oldPin, String newPin) {
        if (!userAuthenticated || currentCard == null) {
            return false;
        }
        
        try {
            currentCard.changePin(oldPin, newPin);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void endSession() {
        userAuthenticated = false;
        cardReader.ejectCard();
    }
}