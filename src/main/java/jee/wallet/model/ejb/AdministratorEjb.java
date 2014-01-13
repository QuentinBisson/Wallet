package jee.wallet.model.ejb;

import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.User;
import org.apache.commons.lang.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AdministratorEjb extends AbstractEjb
        implements CrudInterface<Administrator> {

    private static final String SELECT_BY_ID = "SELECT a FROM Administrator a WHERE a.id=:id";
    private static final String SELECT_ALL = "SELECT a FROM Administrator a";
    private static final String COUNT_ALL = "SELECT COUNT(a) FROM Administrator a";

    @Override
    public void create(Administrator administrator) {
        if (administrator == null) {
            throw new IllegalArgumentException("The administrator must be not null.");
        }
        if (em.contains(administrator)) {
            throw new IllegalStateException("The administrator is in an invalid state.");
        }

        try {
            UserEjb.hashPassword(administrator);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }
        em.persist(administrator);
        em.flush();
    }

    @Override
    public Administrator findById(long id) {
        return (Administrator) em.createQuery(SELECT_BY_ID)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Administrator> findAll(int offset, int limit) {
        return (List<Administrator>) em.createQuery(SELECT_ALL)
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

    private Query createSearchQuery(String prefix, Administrator admin) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder(prefix);
        String separator = " WHERE ";
        if (admin != null) {
            if (StringUtils.isNotBlank(admin.getUsername())) {
                sb.append(separator).append("a.username = %:username%");
                params.put("username", admin.getUsername());
            }
        }

        Query q = em.createQuery(sb.toString());
        setParameters(q, params);
        return q;
    }

    @Override
    public List<Administrator> search(Administrator admin, int offset, int limit) {
        return (List<Administrator>) createSearchQuery(SELECT_ALL, admin)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public int countSearch(Administrator admin) {
        return ((Number) createSearchQuery(COUNT_ALL, admin)
                .getSingleResult())
                .intValue();
    }

    @Override
    public void update(Administrator administrator) {
        if (administrator == null) {
            throw new IllegalArgumentException("The administrator must be not null.");
        }
        if (!em.contains(administrator)) {
            throw new IllegalStateException("The administrator is in an invalid state.");
        }
        User c = findById(administrator.getId());
        if (c == null) {
            throw new IllegalArgumentException("The administrator is invalid.");
        }
        try {
            UserEjb.hashPassword(administrator);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }

        em.persist(administrator);
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The administrator does not exist.");
        }
        Administrator administrator = findById(id);
        delete(administrator);
    }

    @Override
    public void delete(Administrator administrator) {
        if (administrator == null) {
            throw new IllegalArgumentException("The administrator does not exist.");
        }
        em.remove(administrator);
        em.flush();
    }
}