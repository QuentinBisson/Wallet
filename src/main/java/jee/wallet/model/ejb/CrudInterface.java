package jee.wallet.model.ejb;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CrudInterface<E> {

    public void create(E e);

    public E findById(long id);

    public List<E> findByEntity(E e);

    public List<E> findAll();

    public void update(E e);

    public void delete(long id);

    public void delete(E e);

}