package jee.wallet.model.ejb;

import jee.wallet.model.entities.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TransactionEjb extends AbstractEjb implements CrudInterface<Transaction> {
    @EJB
    private StockOptionEjb stockOptionEjb;

    @Override
    public void create(Transaction transaction) {

    }

    @Override
    public Transaction findById(long id) {
        return null;
    }

    @Override
    public List<Transaction> findByEntity(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public void update(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The transaction does not exist.");
        }
        Transaction transaction = findById(id);
        delete(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("The transaction does not exist.");
        }
        em.remove(transaction);
        em.flush();
    }
}