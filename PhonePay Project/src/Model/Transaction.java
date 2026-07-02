package Model;

public class Transaction {
    private String txnId;
    private String sender;
    private String receiver;
    private double amount;
    private String status;
    private String date;

    public Transaction(String txnId, String sender, String receiver,
                       double amount, String status, String date) {
        this.txnId = txnId;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    @Override
    public String toString() {
        return txnId + "," + sender + "," + receiver + "," +
                amount + "," + status + "," + date;
    }

    public static Transaction fromString(String data) {
        String[] d = data.split(",");
        return new Transaction(d[0], d[1], d[2],
                Double.parseDouble(d[3]), d[4], d[5]);
    }
}