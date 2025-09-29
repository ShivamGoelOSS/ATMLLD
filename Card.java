public class Card {
    private String cardNumber;
    private String holderName;
    private String expiryDate;
    private int cvv;
    private String pin;

    public Card(String cardNumber, String holderName, String expiryDate, int cvv, String pin) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void changePin(String oldPin, String newPin) {
        if (validatePin(oldPin)) {
            this.pin = newPin;
        } else {
            throw new IllegalArgumentException("Invalid old PIN");
        }
    }
}