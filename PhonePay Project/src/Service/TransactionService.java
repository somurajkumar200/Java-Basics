package Service;
import Model.BankAccount;
import Model.Transaction;
import Util.DateUtil;
import Util.IdGenerator;
import Exception.InsufficientBalanceException;
import java.util.*;

public class TransactionService {
    private ArrayList<Transaction> transactions;
    public TransactionService(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction sendMoney(BankAccount sender,BankAccount receiver,
                                 double amount) throws Exception {

        if (sender.getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient Balance");
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        Transaction txn = new Transaction(IdGenerator.txnId(),sender.getAccountId(),receiver.getAccountId(),
                                           amount,"SUCCESS",DateUtil.now() );
        transactions.add(txn);
        return txn;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}