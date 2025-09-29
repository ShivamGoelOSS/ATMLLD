public class CardReader {
    private ATM atm;

    public CardReader(ATM atm) {
        this.atm = atm;
    }

    public boolean readCard(Card card) {
        if (card != null) {
            atm.setCurrentCard(card);
            return true;
        }
        return false;
    }

    public void ejectCard() {
        atm.setCurrentCard(null);
        System.out.println("Card ejected");
    }
}