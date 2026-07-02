package Service;
import Model.BankAccount;
import Util.IdGenerator;
import java.util.*;

public class BankService {
    private ArrayList<BankAccount> accounts;
    public BankService(ArrayList<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public BankAccount addAccount(String userId, String bank,
                                  String accNo, String ifsc, double balance) {
        BankAccount account = new BankAccount(IdGenerator.accountId(),userId,bank, accNo, ifsc,balance );
        accounts.add(account);
        return account;
    }

    public List<BankAccount> getUserAccounts(String userId) {
        List<BankAccount> list = new ArrayList<>();

        for (BankAccount b : accounts) {
            if (b.getUserId().equals(userId)) {
                list.add(b);
            }
        }
        return list;
    }

    public BankAccount getAccountById(String accId) {
        for (BankAccount b : accounts) {
            if (b.getAccountId().equals(accId))
                return b;
        }
        return null;
    }

    public void removeAccount(String accId) {

        BankAccount account = null;

        for (BankAccount a : accounts) {
            if (a.getAccountId().equals(accId)) {
                account = a;
                break;
            }
        }

        if (account != null) {
            accounts.remove(account);
            System.out.println("Account removed successfully: " + accId);
        } else {
            System.out.println("Account ID not found. Nothing removed.");
        }
    }

    public List<BankAccount> getAllAccounts() {
        return accounts;
    }
}