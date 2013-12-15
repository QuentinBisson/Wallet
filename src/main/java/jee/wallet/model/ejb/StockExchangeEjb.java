package jee.wallet.model.ejb;

import jee.wallet.model.entities.StockExchange;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StockExchangeEjb extends AbstractEjb
        implements CrudInterface<StockExchange> {
    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private StockOptionEjb stockOptionEjb;

    @Override
    public void create(StockExchange stockExchange) {

    }

    @Override
    public StockExchange findById(long id) {
        return null;
    }

    @Override
    public List<StockExchange> findByEntity(StockExchange stockExchange) {
        return null;
    }

    @Override
    public List<StockExchange> findAll() {
        return null;
    }

    @Override
    public void update(StockExchange stockExchange) {

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The stock exchange does not exist.");
        }
        StockExchange stockExchange = findById(id);
        delete(stockExchange);
    }

    @Override
    public void delete(StockExchange stockExchange) {
        if (stockExchange == null) {
            throw new IllegalArgumentException("The stock exchange does not exist.");
        }
        em.remove(stockExchange);
        em.flush();
    }
}