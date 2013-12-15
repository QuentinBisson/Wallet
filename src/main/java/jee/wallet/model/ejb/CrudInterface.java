package jee.wallet.model.ejb;

import javax.ejb.Remote;

@Remote
public interface CrudInterface<E> {

    public void create(E e);

    public void findById(long id);

    public void findByEntity(E e);

    public void findAll();

    public void update(E e);

    public void delete(long id);

    public void delete(E e);

}