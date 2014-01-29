package jee.wallet.model.ejb;

import jee.wallet.model.entities.StockOption;
import jee.wallet.model.entities.Wallet;

import java.util.List;
import jee.wallet.model.entities.Transaction;
import jee.wallet.model.entities.TransactionType;

public interface WalletEjbInterface extends CrudInterface<Wallet> {
    public void supply(Wallet wallet, double amount);
    public void withdraw(Wallet wallet, double amount);
    public void buyStockOptions(Wallet wallet, List<StockOption> options, TransactionType type);
    public void sellStockOptions(Wallet wallet,List<StockOption> options, TransactionType type);
    public void cancelPrivilegedTransaction(Wallet wallet, Transaction t);
}
