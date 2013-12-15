package jee.wallet.model.ejb;

import jee.wallet.model.entities.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TransactionEjb extends AbstractEjb<Transaction>
        implements CrudInterface<Transaction> {
    @EJB
    private StockOptionEjb stockOptionEjb;

    @Override
    public void create(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findById(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findByEntity(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}