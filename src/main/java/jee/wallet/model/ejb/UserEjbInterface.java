package jee.wallet.model.ejb;

import jee.wallet.model.entities.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserEjbInterface extends CrudInterface<User> {
    User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
