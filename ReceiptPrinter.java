public class ReceiptPrinter {
    public void printReceipt(Transaction transaction) {
        System.out.println("\n===== RECEIPT =====");
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Type: " + transaction.getType());
        System.out.println("Amount: Rs. " + transaction.getAmount());
        System.out.println("Date/Time: " + transaction.getTimestamp());
        System.out.println("Card: XXXX-XXXX-XXXX-" + transaction.getCardNumber().substring(12));
        System.out.println("===================\n");
    }
}