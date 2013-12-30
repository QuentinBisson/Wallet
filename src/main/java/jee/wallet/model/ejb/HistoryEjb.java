package jee.wallet.model.ejb;

import jee.wallet.model.entities.History;

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
public class HistoryEjb extends AbstractEjb implements CrudInterface<History> {
    @EJB
    private CompanyEjb companyEjb;

    private static final String SELECT_BY_ID = "SELECT h FROM History h WHERE h.id=:id";
    private static final String SELECT_ALL = "SELECT h FROM History h";
    private static final String COUNT_ALL = "SELECT COUNT(h) FROM History h";

    @Override
    public void create(History history) {
        if (history == null) {
            throw new IllegalArgumentException("The history must be not null.");
        }
        if (em.contains(history)) {
            throw new IllegalStateException("The history is in an invalid state.");
        }

        em.persist(history);
        em.flush();
    }

    @Override
    public History findById(long id) {
        return (History) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<History> findAll(int offset, int limit) {
        return (List<History>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, History history) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = " WHERE ";
        if (history != null) {
            if (history.getCompany()!= null) {
                sb.append(separator).append("h.company.id = :companyid");
                params.put("companyid", history.getCompany().getId());
                separator = " AND ";
            }
            if (history.getDate() != null) {
                sb.append(separator).append("h.date > :hdate");
                params.put("hdate", history.getDate());
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<History> search(History history, int offset, int limit) {
        return (List<History>) createSearchQuery(SELECT_ALL, history)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(History history) {
        return ((Number) createSearchQuery(COUNT_ALL, history)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(History history) {
        if (history == null) {
            throw new IllegalArgumentException("The history must be not null.");
        }
        if (em.contains(history)) {
            throw new IllegalStateException("The history is in an invalid state.");
        }
        History h = findById(history.getId());
        if (h == null) {
            throw new IllegalArgumentException("The history is invalid.");
        }

        em.persist(history);
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        History history = findById(id);
        delete(history);
    }

    @Override
    public void delete(History history) {
        if (history == null) {
            throw new IllegalArgumentException("The history does not exist.");
        }
        em.remove(history);
        em.flush();
    }
}