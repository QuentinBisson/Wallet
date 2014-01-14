package jee.wallet.model.ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import jee.wallet.model.entities.Company;
import org.apache.commons.io.FileUtils;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StockExchangeEjb extends AbstractEjb
        implements StockExchangeEjbInterface {

    private static final String EXCHANGE_URL = "http://www.nasdaq.com/screening/companies-by-industry.aspx?letter=0&render=download&exchange=";

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
        try {
            return (StockExchange) em.createQuery(SELECT_BY_ID)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
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

    public void realTimeUpdate(StockExchange se) throws MalformedURLException, IOException {

        URL url = new URL(EXCHANGE_URL + se.getName());
        File temp = File.createTempFile("tmp-exchange", ".tmp");
        FileUtils.copyURLToFile(url, temp);

        InputStream in = new FileInputStream(temp);
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        String line;

        se.getCompanies().clear();

        line = buf.readLine();
        while ((line = buf.readLine()) != null) {
            if (StringUtils.isNotBlank(line)) {
                Company company = new Company(line);
                se.getCompanies().add(company);
            }
        }
        buf.close();
        temp.deleteOnExit();
        em.merge(se);

        em.flush();
    }
}
