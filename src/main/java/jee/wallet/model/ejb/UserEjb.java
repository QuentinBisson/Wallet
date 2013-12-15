package jee.wallet.model.ejb;

import jee.wallet.model.entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserEjb extends AbstractEjb<User>
        implements CrudInterface<User>  {

    @Override
    public void create(User user) {

    }

    @Override
    public void findById(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findByEntity(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}