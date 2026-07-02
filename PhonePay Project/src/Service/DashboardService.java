package Service;

import Model.User;
import Model.BankAccount;
import Model.Transaction;
import java.util.*;

public class DashboardService {
    private UserService userService;
    private BankService bankService;
    private TransactionService transactionService;
    private RechargeService rechargeService;
    private Scanner sc;

    public DashboardService(UserService userService,
                            BankService bankService,
                            TransactionService transactionService,
                            RechargeService rechargeService,
                            Scanner sc) {

        this.userService = userService;
        this.bankService = bankService;
        this.transactionService = transactionService;
        this.rechargeService = rechargeService;
        this.sc = sc;
    }

    public void showDashboard(User user) {
        while (true) {
            System.out.println("\n====================================");
            System.out.println("         WELCOME " + user.getName().toUpperCase());
            System.out.println("====================================");
            System.out.println("1. View Profile");
            System.out.println("2. Add Bank Account");
            System.out.println("3. View Accounts");
            System.out.println("4. Remove Account");
            System.out.println("5. Send Money");
            System.out.println("6. Mobile Recharge");
            System.out.println("7. Logout");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1 :
                        viewProfile(user);
                        break;
                    case 2 :
                        addAccount(user);
                        break;
                    case 3 :
                        viewAccounts(user);
                        break;
                    case 4 :
                        removeAccount(user);
                        break;
                    case 5 :
                        sendMoney(user);
                        break;
                    case 6 :
                        recharge(user);
                        break;
                    case 7 :
                        System.out.println("Logged out successfully...");
                        return;
                    default :
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void viewProfile(User user) {
        System.out.println("\n----- PROFILE -----");
        System.out.println("User ID : " + user.getUserId());
        System.out.println("Name    : " + user.getName());
        System.out.println("Mobile  : " + user.getMobile());
        System.out.println("UPI     : " + user.getUpiId());
    }

    private void addAccount(User user) {
        System.out.print("Bank Name: ");
        String bank = sc.nextLine();
        System.out.print("Account Number: ");
        String acc = sc.nextLine();
        System.out.print("IFSC: ");
        String ifsc = sc.nextLine();
        double bal = 10000;
        System.out.println("Balance: " + bal);
        BankAccount account = bankService.addAccount(
                user.getUserId(), bank, acc, ifsc, bal
        );
        System.out.println("Account Added Successfully");
        System.out.println("Account ID: " + account.getAccountId());
    }

    private void viewAccounts(User user) {
        List<BankAccount> list = bankService.getUserAccounts(user.getUserId());
        System.out.println("----- ACCOUNTS -----");
        for (BankAccount b : list) {
            System.out.println("Account ID: " + b.getAccountId() + " | " + "Bank Name: " + b.getBankName() +
                    " | " + "Balanace: " + b.getBalance());
        }
    }

    private void removeAccount(User user) {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine();
        bankService.removeAccount(id);
        System.out.println("Account removed.");
    }

    private void sendMoney(User user) throws Exception {
        List<BankAccount> list = bankService.getUserAccounts(user.getUserId());
        if (list.isEmpty()) {
            System.out.println("No accounts found");
            return;
        }
        System.out.println("Select Sender Account:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getAccountId());
        }
        int idx = Integer.parseInt(sc.nextLine()) - 1;

        if (idx < 0 || idx >= list.size()) {
            System.out.println("Invalid account selection");
            return;
        }

        BankAccount sender = list.get(idx);
        System.out.print("Enter Receiver Account ID: ");
        String receiverId = sc.nextLine();
        BankAccount receiver = bankService.getAccountById(receiverId);

        if (receiver == null) {
            System.out.println("Receiver account not found");
            return;
        }

        System.out.print("Amount: ");
        double amt = Double.parseDouble(sc.nextLine());

        if (amt <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        try {
            Transaction txn = transactionService.sendMoney(sender, receiver, amt);
            System.out.println("Transaction Successful");;

        } catch (Exception e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }

    private void recharge(User user) throws Exception {

        List<BankAccount> list = bankService.getUserAccounts(user.getUserId());

        System.out.println("Select Account:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getAccountId());
        }
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        BankAccount acc = list.get(idx);
        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();
        System.out.print("Amount: ");
        double amt = Double.parseDouble(sc.nextLine());
        System.out.println("Recharge Successful: ");
        Transaction txn = rechargeService.mobileRecharge(acc,amt,mobile);
        System.out.println(txn);
    }
}