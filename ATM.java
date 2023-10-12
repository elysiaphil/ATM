import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ATM {

    private HashMap<String, Double> accounts;

    public ATM() {
        accounts = new HashMap<String, Double>();
    }

    public void openAccount(String userId, double amount) {
        if (accounts.containsKey(userId)) {
            throw new Error("User: " + userId + " already exists.");
        }
        accounts.put(userId, amount);
    }

    public void closeAccount(String userId) {
        if (accounts.containsKey(userId)) {
            if (checkBalance(userId) == 0) {
                accounts.remove(userId);
            }
            throw new Error("You need to withdraw $$$ before closing!");
        }
        throw new Error("Sorry, " + userId + " does not exist.");
    }

    public double checkBalance(String userId) {
        if (accounts.containsKey(userId)) {
            return accounts.get(userId);
        }
        throw new Error("No account found.");
    }

    public double depositMoney(String userId, double amount) {
        if (!accounts.containsKey(userId)) {
            throw new Error("Account doesn't exist, " + userId + " is broke AF.");
        }
        double addedBalance = accounts.get(userId) + amount;
        accounts.replace(userId, addedBalance);

        return addedBalance;

    }

    public double withdrawMoney(String userId, double amount) {
        if (accounts.containsKey(userId)) {
            double currentBal = accounts.get(userId);
            if (currentBal >= amount) {
                double newBal = currentBal - amount;
                accounts.replace(userId, newBal);
                return newBal;
            }
            throw new Error("Sorry " + userId + " , you're broke AF!");
        } else {
            throw new Error("User does not exist.");
        }
    }

    public boolean transferMoney(String fromAccount, String toAccount, double amount) {
        if (amount < 0) {
            return false;
        }
        if (accounts.containsKey(fromAccount) && accounts.containsKey(toAccount)) {
            withdrawMoney(fromAccount, amount);
            depositMoney(toAccount, amount);
            return true;
        }
        return false;
    }

    public void audit() throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("AccountAudit.txt")));
        for (Map.Entry<String, Double> element : accounts.entrySet()) {
            writer.println("UserID: " + element.getKey() + " Balance: " + element.getValue());
        }
        writer.close();
    }
}