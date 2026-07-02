package Main;

import Model.*;
import Service.*;
import java.util.*;

public class PhonePayApplication {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Load data from Files
        HashMap<String, User> users = Service.FileService.loadUsers();
        ArrayList<BankAccount> accounts = Service.FileService.loadAccounts();
        ArrayList<Transaction> transactions = new ArrayList<>();

        // Service
        UserService userService = new UserService(users);
        BankService bankService = new BankService(accounts);
        TransactionService txnService = new TransactionService(transactions);
        RechargeService rechargeService = new RechargeService(transactions);
        AuthenticationService authService = new AuthenticationService(users);
        DashboardService dashboard = new DashboardService(userService,bankService,txnService,
                                                                         rechargeService,sc);


        while (true) {

            System.out.println("==============================");
            System.out.println("      PHONE PAY APPLICATION   ");
            System.out.println("==============================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(sc.nextLine());  //Type Conversion(parsing)

            try {
                if(choice == 1){
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Mobile: ");
                    String mobile = sc.nextLine();

                    User user = userService.registerUser(name, mobile);

                    Service.FileService.saveUsers(userService.getAllUsers());

                    System.out.println("USER ID: " + user.getUserId());
                    System.out.println("UPI ID: " + user.getUpiId());
                    System.out.println("REGISTRATION SUCCESSFULLY");

                } else if (choice == 2) {
                    System.out.print("Mobile: ");
                    String mobile = sc.nextLine();

                    User user = authService.login(mobile, sc);

                    dashboard.showDashboard(user);

                } else if (choice == 3) {
                    System.out.println("Thank you for using PhonePay App");
                    break;

                } else{
                    System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}