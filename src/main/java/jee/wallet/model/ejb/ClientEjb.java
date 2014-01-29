package jee.wallet.model.ejb;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientStatusType;
import jee.wallet.model.entities.ClientType;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.OperationType;
import jee.wallet.model.entities.StockOption;
import jee.wallet.model.entities.Transaction;
import jee.wallet.model.entities.TransactionType;
import jee.wallet.model.entities.Wallet;

@Stateless
@LocalBean
public class ClientEjb extends AbstractEjb implements ClientEjbInterface {

    @EJB
    private WalletEjb walletEjb;
    @EJB
    private CompanyEjb companyEjb;
    
    private static final String SELECT_BY_ID = "SELECT c FROM Client c WHERE c.id=:id";
    private static final String SELECT_ALL = "SELECT c FROM Client c";
    private static final String COUNT_ALL = "SELECT COUNT(c) FROM Client c";

    @Override
    public void create(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        if (em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }
        Wallet wallet = new Wallet();
        wallet.setBalance(0d);
        client.setWallet(wallet);
        
        try {
            UserEjb.hashPassword(client);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }
        
        if (client.getStatus() == null) {
            client.setStatus(ClientStatusType.OPEN);
        }
        if (client.getType() == null) {
            client.setType(ClientType.NORMAL);
        }
        em.persist(client);
        walletEjb.create(wallet);
        em.flush();
    }

    @Override
    public Client findById(long id) {
        return (Client) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Client> findAll(int offset, int limit) {
        return (List<Client>) em.createQuery(SELECT_ALL)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countAll() {
        return ((Number) em.createQuery(COUNT_ALL)
                .getSingleResult())
                .intValue();
    }

    private Query createSearchQuery(String prefix, Client client) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = "WHERE ";
        if (client != null) {
            if (StringUtils.isNotBlank(client.getUsername())) {
                sb.append(separator).append("c.username = %:username%");
                params.put("username", client.getUsername());
                separator = " AND ";
            }
            if (StringUtils.isNotBlank(client.getFirstName())) {
                sb.append(separator).append("c.firstname = %:firstname%");
                params.put("firstname", client.getFirstName());
                separator = " AND ";
            }
            if (StringUtils.isNotBlank(client.getLastName())) {
                sb.append(separator).append("c.lastname = %:lastname%");
                params.put("lastname", client.getLastName());
                separator = " AND ";
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<Client> search(Client client, int offset, int limit) {
        return (List<Client>) createSearchQuery(SELECT_ALL, client)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(Client client) {
        return ((Number) createSearchQuery(COUNT_ALL, client)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        Client c = findById(client.getId());
        if (c == null) {
            throw new IllegalArgumentException("The client is invalid.");
        }
        try {
            UserEjb.hashPassword(client);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }        
        em.merge(client);
        walletEjb.update(client.getWallet());
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        Client client = findById(id);
        delete(client);
    }

    @Override
    public void delete(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        client = em.merge(client);
        em.remove(client);
        em.flush();
    }

    @Override
    public void buyStockOptions(Client client, Company company,
            int amount, TransactionType type) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }

        if (company == null) {
            throw new IllegalArgumentException("The company must be not null.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("The supplied amount can't be 0 or under");
        }
        if (companyEjb.getAmountForActions(company, amount, OperationType.PURCHASE) 
                > client.getWallet().getBalance()) {
            throw new IllegalArgumentException("You're not rich enough");
        }
        List<StockOption> options = new ArrayList<StockOption>();
        for (int i = 0 ; i < amount; i++) {
            StockOption option = new StockOption();
            option.setCompany(company);
            company.getOptions().add(option);
            company.getStockExchange().getOptions().add(option);
            options.add(option);
        }
        walletEjb.buyStockOptions(client.getWallet(), options, type);
    }

    @Override
    public void supply(Client client, double amount) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        if (!em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("The supplied amount can't be 0 or under");
        }
        walletEjb.supply(client.getWallet(), amount);
    }

    @Override
    public void withdraw(Client client, double amount) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        if (!em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }

        if (amount >= 0) {
            throw new IllegalArgumentException("The supplied amount can't be 0 or over");
        }
        walletEjb.withdraw(client.getWallet(), amount);
    }

    @Override
    public void sellStockOptions(Client client, Company company,
            int amount, TransactionType type) {
        List<StockOption> options = client.getWallet().getOptionsForCompany(company);
        if (options.size() < amount && ClientType.NORMAL == client.getType()) {
            throw new IllegalArgumentException("You don't have enough options");
        }
        options = options.subList(0, amount);
        walletEjb.sellStockOptions(client.getWallet(), options, type);
    }
    
    @Override
    public void cancelPrivilegedTransaction(Client client, Transaction t) {
        if (client == null) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        if (t == null || t.getTransactionType() == TransactionType.NORMAL) {
            throw new IllegalArgumentException("The transaction does not "
                    + "exist or is invalid.");
        }
        walletEjb.cancelPrivilegedTransaction(client.getWallet(), t);
    }

    @Override
    public void closeAccount(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        client.setStatus(ClientStatusType.CLOSED);
        update(client);
    }
}