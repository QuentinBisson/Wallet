package jee.wallet.model.ejb;

import jee.wallet.model.entities.Company;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompanyEjb extends AbstractEjb<Company> implements CrudInterface<Company> {

    @EJB
    private HistoryEjb historyEjb;
    @EJB
    private StockOptionEjb stockOptionEjb;
    @EJB
    private StockExchangeEjb stockExchangeEjb;

    @Override
    public void create(Company company) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findById(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findByEntity(Company company) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Company company) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Company company) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}