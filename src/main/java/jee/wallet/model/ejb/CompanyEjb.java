package jee.wallet.model.ejb;

import jee.wallet.model.entities.Company;
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
public class CompanyEjb extends AbstractEjb implements CrudInterface<Company> {

    @EJB
    private HistoryEjb historyEjb;
    @EJB
    private StockOptionEjb stockOptionEjb;
    @EJB
    private StockExchangeEjb stockExchangeEjb;

    private static final String SELECT_BY_ID = "SELECT c FROM Company c WHERE c.id=:id";
    private static final String SELECT_ALL = "SELECT c FROM Company c";
    private static final String COUNT_ALL = "SELECT COUNT(c) FROM Company c";

    @Override
    public void create(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("The company must be not null.");
        }
        if (company.getStockExchanges().isEmpty()) {
            throw new IllegalStateException("The company is in an invalid state.");
        }
        if (em.contains(company)) {
            throw new IllegalStateException("The company is in an invalid state.");
        }

        em.persist(company);
        em.flush();
    }

    @Override
    public Company findById(long id) {
        return (Company) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Company> findAll(int offset, int limit) {
        return (List<Company>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, Company company) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = " WHERE ";
        if (company != null) {
            if (StringUtils.isNotBlank(company.getCode())) {
                sb.append(separator).append("c.code = :code");
                params.put("code", company.getCode());
                separator = " AND ";
            }
            if (StringUtils.isNotBlank(company.getName())) {
                sb.append(separator).append("c.name = %:name%");
                params.put("name", company.getName());
                separator = " AND ";
            }
            if (StringUtils.isNotBlank(company.getSector())) {
                sb.append(separator).append("c.sector = %:sector%");
                params.put("sector", company.getName());
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<Company> search(Company company, int offset, int limit) {
        return (List<Company>) createSearchQuery(SELECT_ALL, company)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(Company company) {
        return ((Number) createSearchQuery(COUNT_ALL, company)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("The company must be not null.");
        }
        if (company.getStockExchanges().isEmpty()) {
            throw new IllegalStateException("The company is in an invalid state.");
        }
        if (em.contains(company)) {
            throw new IllegalStateException("The company is in an invalid state.");
        }
        Company c = findById(company.getId());
        if (c == null) {
            throw new IllegalArgumentException("The company is invalid.");
        }

        em.persist(company);
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The company does not exist.");
        }
        Company company = findById(id);
        delete(company);
    }

    @Override
    public void delete(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("The company does not exist.");
        }
        em.remove(company);
        em.flush();
    }
}