package jee.wallet.model.ejb;

import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.Wallet;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClientEjb extends AbstractEjb
        implements CrudInterface<Client> {
    @EJB
    private WalletEjb walletEjb;

    private String hashPassword(String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest digest = null;
        digest = MessageDigest.getInstance("SHA-512");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        return hash.toString();
    }

    @Override
    public void create(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        if (em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }
        Wallet wallet = new Wallet();
        client.setWallet(wallet);
        wallet.setClient(client);

        try {
            String password = hashPassword(client.getPassword());
            client.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }

        em.persist(client);
        walletEjb.create(wallet);
        em.flush();
    }

    @Override
    public Client findById(long id) {
        //TODO
        return null;
    }

    @Override
    public List<Client> findByEntity(Client client) {
        //TODO
        return null;
    }

    @Override
    public List<Client> findAll() {
        //TODO
        return null;
    }

    @Override
    public void update(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client must be not null.");
        }
        if (!em.contains(client)) {
            throw new IllegalStateException("The client is in an invalid state.");
        }
        Client c = findById(client.getId());
        if (c == null) {
            throw new IllegalArgumentException("The client is invalid.");
        }

        //Check if password has changed
        try {
            String password = hashPassword(client.getPassword());
            if (!StringUtils.equals(password, c.getPassword())) {
                client.setPassword(password);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing hash algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Missing hash algorithm");
        }

        em.persist(client);
        walletEjb.update(client.getWallet());
        em.flush();
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        Client client = findById(id);
        delete(client);
    }

    @Override
    public void delete(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client does not exist.");
        }
        em.remove(client);
        em.flush();
    }
}