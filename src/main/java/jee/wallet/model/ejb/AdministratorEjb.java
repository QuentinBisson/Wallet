package jee.wallet.model.ejb;

import jee.wallet.model.entities.Administrator;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AdministratorEjb extends AbstractEjb
        implements CrudInterface<Administrator> {

    @Override
    public void create(Administrator administrator) {

    }

    @Override
    public Administrator findById(long id) {
        return null;
    }

    @Override
    public List<Administrator> findByEntity(Administrator administrator) {
        return null;
    }

    @Override
    public List<Administrator> findAll() {
        return null;
    }

    @Override
    public void update(Administrator administrator) {

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The administrator does not exist.");
        }
        Administrator administrator = findById(id);
        delete(administrator);
    }

    @Override
    public void delete(Administrator administrator) {
        if (administrator == null) {
            throw new IllegalArgumentException("The administrator does not exist.");
        }
        em.remove(administrator);
        em.flush();
    }
}