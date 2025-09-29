## Class Diagram

```mermaid
classDiagram
    class ATM {
        -String atmId
        -String location
        -Card currentCard
        -boolean userAuthenticated
        +BankService bankService
        +CardReader cardReader
        +CashDispenser cashDispenser
        +ReceiptPrinter receiptPrinter
        +PinPad pinPad
        +Screen screen
        +setCurrentCard(Card)
        +authenticateUser(String) boolean
        +withdraw(double) boolean
        +deposit(double) boolean
        +getMiniStatement() List~Transaction~
        +changePin(String, String) boolean
        +endSession() void
    }
    
    class Card {
        -String cardNumber
        -String holderName
        -String expiryDate
        -int cvv
        -String pin
        +getCardNumber() String
        +getHolderName() String
        +validatePin(String) boolean
        +changePin(String, String) void
    }
    
    class CardReader {
        -ATM atm
        +readCard(Card) boolean
        +ejectCard() void
    }
    
    class CashDispenser {
        -Map~Integer, Integer~ availableCash
        +dispenseCash(double) boolean
        -calculateNotesToDispense(int) Map~Integer, Integer~
        -getTotalAvailableCash() int
    }
    
    class BankService {
        -Map~String, Double~ accountBalances
        -Map~String, List~Transaction~~ transactionHistory
        +validateBalance(String, double) boolean
        +processTransaction(Transaction) void
        +getBalance(String) double
        +getTransactionHistory(String) List~Transaction~
    }
    
    class Transaction {
        -String transactionId
        -TransactionType type
        -double amount
        -String cardNumber
        -LocalDateTime timestamp
        +getTransactionId() String
        +getType() TransactionType
        +getAmount() double
        +getCardNumber() String
        +getTimestamp() LocalDateTime
    }
    
    class ReceiptPrinter {
        +printReceipt(Transaction) void
    }
    
    class PinPad {
        +getPin() String
    }
    
    class Screen {
        +displayMessage(String) void
        +displayMainMenu() void
    }
    
    class Client {
        +main(String[]) void
    }
    
    class TransactionType {
        <<enumeration>>
        WITHDRAWAL
        DEPOSIT
        BALANCE_INQUIRY
        PIN_CHANGE
    }
    
    ATM --> Card : has
    ATM --> CardReader : has
    ATM --> CashDispenser : has
    ATM --> ReceiptPrinter : has
    ATM --> PinPad : has
    ATM --> Screen : has
    ATM --> BankService : has
    CardReader --> ATM : references
    Transaction --> TransactionType : has
    BankService --> Transaction : manages
    Client --> ATM : uses
