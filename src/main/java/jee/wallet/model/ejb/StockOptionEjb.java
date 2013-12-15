package jee.wallet.model.ejb;

import jee.wallet.model.entities.StockOption;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StockOptionEjb extends AbstractEjb<StockOption> implements CrudInterface<StockOption>  {
    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private StockExchangeEjb stockExchangeEjb;
    @Override
    public void create(StockOption stockOption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findById(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findByEntity(StockOption stockOption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(StockOption stockOption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(StockOption stockOption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}