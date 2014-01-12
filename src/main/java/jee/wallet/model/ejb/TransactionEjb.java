package jee.wallet.model.ejb;

import jee.wallet.model.entities.Transaction;

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
public class TransactionEjb extends AbstractEjb implements CrudInterface<Transaction> {
    @EJB
    private StockOptionEjb stockOptionEjb;

    private static final String SELECT_BY_ID = "SELECT t FROM Transaction t WHERE t.id=:id";
    private static final String SELECT_ALL = "SELECT t FROM Transaction t";
    private static final String COUNT_ALL = "SELECT COUNT(t) FROM Transaction t";

    @Override
    public void create(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("The transaction must be not null.");
        }
        if (em.contains(transaction)) {
            throw new IllegalStateException("The transaction is in an invalid state.");
        }

        em.persist(transaction);
        em.flush();
    }

    @Override
    public Transaction findById(long id) {
        return (Transaction) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Transaction> findAll(int offset, int limit) {
        return (List<Transaction>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, Transaction transaction) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = " WHERE ";
        if (transaction != null) {
            if (transaction.getTransactionDate() != null) {
                sb.append(separator).append("t.date >= :tdate");
                params.put("tdate", transaction.getTransactionDate());
            }
            if (transaction.getPrice() > 0) {
                sb.append(separator).append("t.price >= :price");
                params.put("price", transaction.getPrice());
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<Transaction> search(Transaction transaction, int offset, int limit) {
        return (List<Transaction>) createSearchQuery(SELECT_ALL, transaction)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(Transaction transaction) {
        return ((Number) createSearchQuery(COUNT_ALL, transaction)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("The transaction must be not null.");
        }
        if (em.contains(transaction)) {
            throw new IllegalStateException("The transaction is in an invalid state.");
        }
        Transaction t = findById(transaction.getId());
        if (t == null) {
            throw new IllegalArgumentException("The transaction is invalid.");
        }

        em.persist(transaction);
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The transaction does not exist.");
        }
        Transaction transaction = findById(id);
        delete(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("The transaction does not exist.");
        }
        em.remove(transaction);
        em.flush();
    }
}