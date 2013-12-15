package jee.wallet.model.ejb;

import jee.wallet.model.entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserEjb extends AbstractEjb
        implements CrudInterface<User>  {

    @Override
    public void create(User user) {

    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findByEntity(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The user does not exist.");
        }
        User user = findById(id);
        delete(user);
    }

    @Override
    public void delete(User user) {
        if (user == null) {
            throw new IllegalArgumentException("The user does not exist.");
        }
        em.remove(user);
        em.flush();
    }
}