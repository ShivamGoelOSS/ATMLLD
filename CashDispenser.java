import java.util.HashMap;
import java.util.Map;

public class CashDispenser {
    private Map<Integer, Integer> availableCash; // denomination -> count

    public CashDispenser() {
        availableCash = new HashMap<>();
        // Initialize with some cash
        availableCash.put(2000, 50);  // 2000 rupee notes
        availableCash.put(500, 100);  // 500 rupee notes
        availableCash.put(200, 100);  // 200 rupee notes
        availableCash.put(100, 100);  // 100 rupee notes
    }

    public boolean dispenseCash(double amount) {
        int amountInRupees = (int) amount;
        
        // Check if we have enough cash
        if (getTotalAvailableCash() < amountInRupees) {
            System.out.println("Insufficient cash in ATM");
            return false;
        }

        // Calculate notes to dispense
        Map<Integer, Integer> notesToDispense = calculateNotesToDispense(amountInRupees);
        if (notesToDispense == null) {
            System.out.println("Cannot dispense the exact amount");
            return false;
        }

        // Update available cash
        for (Map.Entry<Integer, Integer> entry : notesToDispense.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            availableCash.put(denomination, availableCash.get(denomination) - count);
        }

        // Print dispensed notes
        System.out.println("Dispensing cash:");
        for (Map.Entry<Integer, Integer> entry : notesToDispense.entrySet()) {
            System.out.println(entry.getValue() + " notes of " + entry.getKey() + " rupees");
        }

        return true;
    }

    private Map<Integer, Integer> calculateNotesToDispense(int amount) {
        Map<Integer, Integer> result = new HashMap<>();
        int remainingAmount = amount;

        // Try to dispense from highest denomination to lowest
        Integer[] denominations = availableCash.keySet().toArray(new Integer[0]);
        java.util.Arrays.sort(denominations, java.util.Collections.reverseOrder());

        for (int denomination : denominations) {
            int availableCount = availableCash.get(denomination);
            int neededCount = remainingAmount / denomination;
            int countToDispense = Math.min(availableCount, neededCount);

            if (countToDispense > 0) {
                result.put(denomination, countToDispense);
                remainingAmount -= denomination * countToDispense;
            }
        }

        // Check if we could dispense the exact amount
        if (remainingAmount == 0) {
            return result;
        } else {
            return null;
        }
    }

    private int getTotalAvailableCash() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : availableCash.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }
}