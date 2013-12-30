package jee.wallet.model.ejb;

import jee.wallet.model.entities.StockExchange;
import org.apache.commons.lang.StringUtils;

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
public class StockExchangeEjb extends AbstractEjb
        implements CrudInterface<StockExchange> {
    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private StockOptionEjb stockOptionEjb;

    private static final String SELECT_BY_ID = "SELECT se FROM StockExchange se WHERE se.id=:id";
    private static final String SELECT_ALL = "SELECT se FROM StockExchange se";
    private static final String COUNT_ALL = "SELECT COUNT(se) FROM StockExchange se";

    @Override
    public void create(StockExchange stockExchange) {
        if (stockExchange == null) {
            throw new IllegalArgumentException("The stock exchange must be not null.");
        }
        if (em.contains(stockExchange)) {
            throw new IllegalStateException("The stock exchange is in an invalid state.");
        }

        em.persist(stockExchange);
        em.flush();
    }


    @Override
    public StockExchange findById(long id) {
        return (StockExchange) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<StockExchange> findAll(int offset, int limit) {
        return (List<StockExchange>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, StockExchange stockExchange) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = " WHERE ";
        if (stockExchange != null) {
            if (StringUtils.isNotBlank(stockExchange.getName())) {
                sb.append(separator).append("se.name = :name");
                params.put("name", stockExchange.getName());
                separator = " AND ";
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<StockExchange> search(StockExchange stockExchange, int offset, int limit) {
        return (List<StockExchange>) createSearchQuery(SELECT_ALL, stockExchange)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(StockExchange stockExchange) {
        return ((Number) createSearchQuery(COUNT_ALL, stockExchange)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(StockExchange stockExchange) {
        if (stockExchange == null) {
            throw new IllegalArgumentException("The stock exchange must be not null.");
        }
        if (em.contains(stockExchange)) {
            throw new IllegalStateException("The stock exchange is in an invalid state.");
        }
        StockExchange se = findById(stockExchange.getId());
        if (se == null) {
            throw new IllegalArgumentException("The stock exchange is invalid.");
        }

        em.persist(stockExchange);
        em.flush();

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The stock exchange does not exist.");
        }
        StockExchange stockExchange = findById(id);
        delete(stockExchange);
    }

    @Override
    public void delete(StockExchange stockExchange) {
        if (stockExchange == null) {
            throw new IllegalArgumentException("The stock exchange does not exist.");
        }
        em.remove(stockExchange);
        em.flush();
    }
}