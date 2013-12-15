package jee.wallet.model.ejb;

import jee.wallet.model.entities.Company;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompanyEjb extends AbstractEjb implements CrudInterface<Company> {

    @EJB
    private HistoryEjb historyEjb;
    @EJB
    private StockOptionEjb stockOptionEjb;
    @EJB
    private StockExchangeEjb stockExchangeEjb;

    @Override
    public void create(Company company) {

    }

    @Override
    public Company findById(long id) {
        return null;
    }

    @Override
    public List<Company> findByEntity(Company company) {
        return null;
    }

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The company does not exist.");
        }
        Company company = findById(id);
        delete(company);
    }

    @Override
    public void delete(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("The company does not exist.");
        }
        em.remove(company);
        em.flush();
    }
}