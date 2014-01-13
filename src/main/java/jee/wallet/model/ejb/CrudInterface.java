package jee.wallet.model.ejb;

import java.util.List;

public interface CrudInterface<E> {

    public void create(E e);

    public E findById(long id);

    public List<E> search(E e, int offset, int limit);
    public int countSearch(E e);
    public List<E> findAll(int offset, int limit);
    public int countAll();

    public void update(E e);

    public void delete(long id);

    public void delete(E e);

}