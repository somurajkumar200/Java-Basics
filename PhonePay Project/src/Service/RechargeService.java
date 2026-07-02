package Service;

import Model.BankAccount;
import Model.Transaction;
import Util.DateUtil;
import Util.IdGenerator;
import Exception.InsufficientBalanceException;
import java.util.ArrayList;

public class RechargeService {
    private ArrayList<Transaction> transactions;
    public RechargeService(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction mobileRecharge(BankAccount account, double amount,
                                      String mobile) throws Exception {

        if (account.getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient balance");
        account.setBalance(account.getBalance() - amount);
        Transaction txn = new Transaction(IdGenerator.txnId(),account.getAccountId(),mobile,
                                           amount,"RECHARGE SUCCESS",DateUtil.now());
        transactions.add(txn);
        return txn;
    }
}