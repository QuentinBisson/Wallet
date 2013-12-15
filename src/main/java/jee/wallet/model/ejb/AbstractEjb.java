package jee.wallet.model.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractEjb<E> {
	
	@PersistenceContext
	protected EntityManager em;
	
	public void findById(long id) {
	
	}
	
}