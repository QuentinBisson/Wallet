package jee.wallet.model.ejb;

import jee.wallet.model.entities.History;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistoryEjb extends AbstractEjb implements CrudInterface<History> {
    @EJB
    private CompanyEjb companyEjb;

    @Override
    public void create(History history) {

    }

    @Override
    public History findById(long id) {
        return null;
    }

    @Override
    public List<History> findByEntity(History history) {
        return null;
    }

    @Override
    public List<History> findAll() {
        return null;
    }

    @Override
    public void update(History history) {

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        History history = findById(id);
        delete(history);
    }

    @Override
    public void delete(History history) {
        if (history == null) {
            throw new IllegalArgumentException("The history does not exist.");
        }
        em.remove(history);
        em.flush();
    }
}