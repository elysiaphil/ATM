import java.io.IOException;

public class ATMTester {
    public static void main(String args[]) throws IOException {
        ATM atm = new ATM();
        atm.openAccount("clairesaccount", 100);
        atm.openAccount("mikesaccount", 1000);
        System.out.println(atm.checkBalance("clairesaccount"));
        System.out.println(atm.depositMoney("clairesaccount", 500));
        System.out.println(atm.withdrawMoney("mikesaccount", 120));
        // System.out.println(atm.transferMoney("clairesaccount", "clairesaccount",
        // 500));

        // atm.closeAccount("clairesaccount");
        atm.audit();

    }

}
