package jee.wallet.model.ejb;

import jee.wallet.model.entities.Wallet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class WalletEjb extends AbstractEjb implements CrudInterface<Wallet> {
    @EJB
    private TransactionEjb transactionEjb;
    @EJB
    private ClientEjb clientEjb;

    private static final String SELECT_BY_ID = "SELECT w FROM Wallet w WHERE w.id=:id";
    private static final String SELECT_ALL = "SELECT w FROM Wallet w";
    private static final String COUNT_ALL = "SELECT COUNT(w) FROM Wallet w";

    @Override
    public void create(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet must be not null.");
        }
        if (wallet.getClient() == null) {
            throw new IllegalStateException("The wallet is in an invalid state.");
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
        String separator = " WHERE ";
        if (wallet != null) {
            if (wallet.getClient() != null) {
                sb.append(separator).append("w.client.id = :clientid");
                params.put("clientid", wallet.getClient().getId());
            }
        }

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
        if (wallet.getClient() == null) {
            throw new IllegalStateException("The wallet is in an invalid state.");
        }
        if (em.contains(wallet)) {
            throw new IllegalStateException("The wallet is in an invalid state.");
        }
        Wallet w = findById(wallet.getId());
        if (w == null) {
            throw new IllegalArgumentException("The wallet is invalid.");
        }

        em.persist(wallet);
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
        em.remove(wallet);
        em.flush();
    }
}