package jee.wallet.model.ejb;

import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.Transaction;
import jee.wallet.model.entities.TransactionType;

public interface ClientEjbInterface extends CrudInterface<Client> {
    void supply(Client client, double amount);
    void withdraw(Client client, double amount);

    void buyStockOptions(Client client, Company company,
            int amount, TransactionType type);
    void sellStockOptions(Client client, Company company,
            int amount, TransactionType type);
    void cancelPrivilegedTransaction(Client client, Transaction transaction);
    
    void closeAccount(Client client);
}
