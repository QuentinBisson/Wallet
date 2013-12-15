package jee.wallet.model.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractEjb<E> implements EjbInterface {
	
	@PersistenceContext
	protected EntityManager em;
	
	public void findById(long id) {
		return em.find(E.getClass(), id);
	}
	
}