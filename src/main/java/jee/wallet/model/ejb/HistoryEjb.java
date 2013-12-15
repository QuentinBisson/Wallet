package jee.wallet.model.ejb;

import jee.wallet.model.entities.History;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistoryEjb extends AbstractEjb<History> implements CrudInterface<History> {
    @EJB
    private CompanyEjb companyEjb;

    @Override
    public void create(History history) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findById(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findByEntity(History history) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(History history) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(History history) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}