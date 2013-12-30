package jee.wallet.model.ejb;

import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockExchange;
import jee.wallet.model.entities.StockOption;
import jee.wallet.model.entities.Wallet;

import java.util.List;

public interface WalletEjbInterface extends CrudInterface<Wallet> {
    public void supply(Wallet wallet, double amount);
    public void withdraw(Wallet wallet, double amount);
    public void buyStockOptions(Wallet wallet, List<StockOption> options);
    public void sellStockOptions(Wallet wallet,List<StockOption> options);

    List<StockOption> getOptionsForCompanyInStockExchange(
           Wallet wallet, Company company, StockExchange stockExchange);
}
