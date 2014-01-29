package jee.wallet.model.ejb;

import java.util.Date;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockOption;
import jee.wallet.model.entities.Wallet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import jee.wallet.model.entities.OperationType;
import jee.wallet.model.entities.Transaction;
import jee.wallet.model.entities.TransactionType;

@Stateless
@LocalBean
public class WalletEjb extends AbstractEjb implements WalletEjbInterface {
    @EJB
    private TransactionEjb transactionEjb;
    @EJB
    private CompanyEjb companyEjb;

    private static final String SELECT_BY_ID = "SELECT w FROM Wallet w WHERE w.id=:id";
    private static final String SELECT_ALL = "SELECT w FROM Wallet w";
    private static final String COUNT_ALL = "SELECT COUNT(w) FROM Wallet w";

    @Override
    public void create(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet must be not null.");
        }
        
        if (em.contains(wallet)) {
            throw new IllegalStateException("The wallet is in an invalid state.");
        }
        em.persist(wallet);
        em.flush();
    }

    @Override
    public Wallet findById(long id) {
        return (Wallet) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Wallet> findAll(int offset, int limit) {
        return (List<Wallet>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, Wallet wallet) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<Wallet> search(Wallet wallet, int offset, int limit) {
        return (List<Wallet>) createSearchQuery(SELECT_ALL, wallet)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(Wallet wallet) {
        return ((Number) createSearchQuery(COUNT_ALL, wallet)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet must be not null.");
        }
        if (em.contains(wallet)) {
            throw new IllegalStateException("The wallet is in an invalid state.");
        }
        Wallet w = findById(wallet.getId());
        if (w == null) {
            throw new IllegalArgumentException("The wallet is invalid.");
        }

        em.merge(wallet);
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        Wallet wallet = findById(id);
        delete(wallet);
    }

    @Override
    public void delete(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        wallet = em.merge(wallet);
        em.remove(wallet);
        em.flush();
    }

    @Override
    public void supply(Wallet wallet, double amount) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("The supplied amount can't be 0 or under");
        }
        wallet.setBalance(wallet.getBalance() + amount);
        update(wallet);
    }

    @Override
    public void withdraw(Wallet wallet, double amount) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        if (amount >= 0) {
            throw new IllegalArgumentException("The supplied amount can't be 0 or over");
        }
        wallet.setBalance(wallet.getBalance() + amount);
        update(wallet);
    }

    @Override
    public void buyStockOptions(Wallet wallet, List<StockOption> options, TransactionType type) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("The options are empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("The transaction type is invalid.");
        }
        Company c = options.get(0).getCompany();
        if (type == TransactionType.NORMAL) {
            withdraw(wallet, -companyEjb.getAmountForActions(c, options.size(),
                OperationType.PURCHASE));
        } else {
            //TODO find the right transaction for suply
            supply(wallet, companyEjb.getAmountForActions(c, options.size(),
                OperationType.SALE));
            withdraw(wallet, -companyEjb.getAmountForActions(c, options.size(),
                OperationType.PURCHASE));
        }
        Transaction t = new Transaction();
        t.setOperationType(OperationType.PURCHASE);
        t.setPrice(c.getLastSale());
        t.setStockOptions(options);
        t.setTransactionDate(new Date());
        t.setTransactionType(type);
        transactionEjb.create(t);
        wallet.getTransactions().add(t);
        update(wallet);
    }
    
    @Override
    public void sellStockOptions(Wallet wallet, List<StockOption> options, TransactionType type) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        
        Company c = options.get(0).getCompany();
        if (type == TransactionType.NORMAL) { 
            supply(wallet, companyEjb.getAmountForActions(c, options.size(),
                OperationType.SALE));
        }
        Transaction t = new Transaction();
        t.setOperationType(OperationType.SALE);
        t.setPrice(c.getLastSale());
        t.setStockOptions(options);
        t.setTransactionDate(new Date());
        t.setTransactionType(type);
        transactionEjb.create(t);
        wallet.getTransactions().add(t);
        update(wallet);
    }
    
    @Override
    public void cancelPrivilegedTransaction(Wallet wallet, Transaction t) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        if (t == null || t.getTransactionType() == TransactionType.NORMAL) {
            throw new IllegalArgumentException("The transaction does not exist or is invalid.");
        }
        wallet.getTransactions().remove(t);
        transactionEjb.delete(t);
        update(wallet);
    }
}