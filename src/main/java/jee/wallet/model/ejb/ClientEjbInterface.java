package jee.wallet.model.ejb;

import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockExchange;

public interface ClientEjbInterface extends CrudInterface<Client> {
    void supply(Client client, double amount);
    void withdraw(Client client, double amount);

    void buyStockOptions(Client client, Company company, StockExchange stockExchange, int amount);
    void sellStockOptions(Client client, Company company, StockExchange stockExchange, int amount);

    void closeAccount(Client client);
}
