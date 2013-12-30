package jee.wallet.model.ejb;

import jee.wallet.model.entities.StockOption;

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
public class StockOptionEjb extends AbstractEjb implements CrudInterface<StockOption>  {
    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private StockExchangeEjb stockExchangeEjb;

    private static final String SELECT_BY_ID = "SELECT so FROM StockOption so WHERE so.id=:id";
    private static final String SELECT_ALL = "SELECT so FROM StockOption so";
    private static final String COUNT_ALL = "SELECT COUNT(so) FROM StockOption so";

    @Override
    public void create(StockOption stockOption) {
        if (stockOption == null) {
            throw new IllegalArgumentException("The stock option must be not null.");
        }
        if (em.contains(stockOption)) {
            throw new IllegalStateException("The stock option is in an invalid state.");
        }

        em.persist(stockOption);
        em.flush();
    }

    @Override
    public StockOption findById(long id) {
        return (StockOption) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<StockOption> findAll(int offset, int limit) {
        return (List<StockOption>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, StockOption stockOption) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = " WHERE ";
        if (stockOption != null) {
            if (stockOption.getCompany() != null) {
                sb.append(separator).append("so.company.id = :companyid");
                params.put("companyid", stockOption.getCompany().getId());
                separator = " AND ";
            }
            if (stockOption.getStockExchange() != null) {
                sb.append(separator).append("so.stockExchange.id = :stockexchangeid");
                params.put("stockexchangeid", stockOption.getStockExchange().getId());
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<StockOption> search(StockOption stockOption, int offset, int limit) {
        return (List<StockOption>) createSearchQuery(SELECT_ALL, stockOption)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(StockOption stockOption) {
        return ((Number) createSearchQuery(COUNT_ALL, stockOption)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(StockOption stockOption) {
        if (stockOption == null) {
            throw new IllegalArgumentException("The stock option must be not null.");
        }
        if (em.contains(stockOption)) {
            throw new IllegalStateException("The stock option is in an invalid state.");
        }
        StockOption so = findById(stockOption.getId());
        if (so == null) {
            throw new IllegalArgumentException("The stock option is invalid.");
        }

        em.persist(stockOption);
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The stock option does not exist.");
        }
        StockOption stockOption = findById(id);
        delete(stockOption);
    }

    @Override
    public void delete(StockOption stockOption) {
        if (stockOption == null) {
            throw new IllegalArgumentException("The stock option does not exist.");
        }
        em.remove(stockOption);
        em.flush();
    }
}