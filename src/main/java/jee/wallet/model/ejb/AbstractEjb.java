package jee.wallet.model.ejb;


import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractEjb<E> implements EjbInterface<E> {

    @PersistenceContext
    protected EntityManager em;

}