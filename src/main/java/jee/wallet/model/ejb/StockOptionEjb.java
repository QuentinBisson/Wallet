package jee.wallet.model.ejb;

import jee.wallet.model.entities.StockOption;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StockOptionEjb extends AbstractEjb implements CrudInterface<StockOption>  {
    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private StockExchangeEjb stockExchangeEjb;

    @Override
    public void create(StockOption stockOption) {

    }

    @Override
    public StockOption findById(long id) {
        return null;
    }

    @Override
    public List<StockOption> findByEntity(StockOption stockOption) {
        return null;
    }

    @Override
    public List<StockOption> findAll() {
        return null;
    }

    @Override
    public void update(StockOption stockOption) {

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The stock option does not exist.");
        }
        StockOption stockOption = findById(id);
        delete(stockOption);
    }

    @Override
    public void delete(StockOption stockOption) {
        if (stockOption == null) {
            throw new IllegalArgumentException("The stock option does not exist.");
        }
        em.remove(stockOption);
        em.flush();
    }
}