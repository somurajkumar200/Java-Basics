package Model;

public class BankAccount {
    private String accountId;
    private String userId;
    private String bankName;
    private String accountNumber;
    private String ifsc;
    private double balance;

    public BankAccount(String accountId, String userId, String bankName,
                       String accountNumber, String ifsc, double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifsc = ifsc;
        this.balance = balance;
    }

    //Get Method
    public String getBankName(){
        return bankName;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getUserId() {
        return userId;
    }
    public double getBalance() {
        return balance;
    }

    //Set Method
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return accountId + "," + userId + "," + bankName + "," +
                accountNumber + "," + ifsc + "," + balance;
    }

    public static BankAccount fromString(String data) {
        String[] d = data.split(",");
        return new BankAccount(d[0], d[1], d[2], d[3], d[4], Double.parseDouble(d[5]));
    }
}