package jee.wallet.model.ejb;

import jee.wallet.model.entities.*;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ClientEjb extends AbstractEjb implements ClientEjbInterface {

    @EJB
    private WalletEjb walletEjb;

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
        
        /*try {
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
        }*/
        em.persist(client);
        walletEjb.create(wallet);
        em.flush();
    }

    @Override
    public Client findById(long id) {
        System.out.println("id : "+id);
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
        System.out.println("Je suis ici");
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
       /*if (!em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }*/
        System.out.println("Je suis la");
        Client c = findById(client.getId());
        if (c == null) {
            throw new IllegalArgumentException("The client is invalid.");
        }
        System.out.println("Je suis icsdflsdki");
        /*try {
            UserEjb.hashPassword(client);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }*/

        System.out.println("client "+client.getUsername());
        System.out.println("balance "+client.getWallet().getBalance());
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
    public void buyStockOptions(Client client, Company company, StockExchange stockExchange, int amount) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        if (!em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }

        if (company == null) {
            throw new IllegalArgumentException("The company must be not null.");
        }
        if (!em.contains(company)) {
            throw new IllegalStateException("The company is in an invalid state.");
        }

        if (stockExchange == null) {
            throw new IllegalArgumentException("The stock exchange must be not null.");
        }
        if (!em.contains(stockExchange)) {
            throw new IllegalStateException("The stock exchange is in an invalid state.");
        }

        List<StockOption> options = walletEjb.getOptionsForCompanyInStockExchange(client.getWallet(),
                company, stockExchange);
        if (amount <= 0 || amount > options.size()) {
            throw new IllegalArgumentException("The supplied amount can't be 0 or under");
        }
        //TODO frais = 0.5%de la transaction
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
    public void sellStockOptions(Client client, Company company, StockExchange stockExchange, int amount) {
        //TODO  frais = 0.5%de la transaction
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