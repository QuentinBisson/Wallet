package jee.wallet.model.ejb;

import jee.wallet.model.entities.Wallet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class WalletEjb extends AbstractEjb implements CrudInterface<Wallet> {
    @EJB
    private TransactionEjb transactionEjb;
    @EJB
    private ClientEjb clientEjb;

    @Override
    public void create(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet must be not null.");
        }
        if (wallet.getClient() == null) {
            throw new IllegalStateException("The wallet is in an invalid state.");
        }
        if (em.contains(wallet)) {
            throw new IllegalStateException("The wallet is in an invalid state.");
        }
        em.persist(wallet);
        em.flush();
    }

    @Override
    public Wallet findById(long id) {
        return null;
    }

    @Override
    public List<Wallet> findByEntity(Wallet wallet) {
        return null;
    }

    @Override
    public List<Wallet> findAll() {
        return null;
    }

    @Override
    public void update(Wallet wallet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        Wallet wallet = findById(id);
        delete(wallet);
    }

    @Override
    public void delete(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("The wallet does not exist.");
        }
        em.remove(wallet);
        em.flush();
    }
}