package jee.wallet.model.ejb;


import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractEjb {

    @PersistenceContext
    protected EntityManager em;

    protected Query setParameters(Query q, Map<String, Object> params) {
        for (String s : params.keySet()) {
            q.setParameter(s, params.get(s));
        }
        return q;
    }

}