package jee.wallet.model.ejb;

import jee.wallet.model.entities.Wallet;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class WalletEjb extends AbstractEjb<Wallet> {
    @Override
    public void create(Wallet wallet) {

    }

    @Override
    public void findById(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findByEntity(Wallet wallet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Wallet wallet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Wallet wallet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}