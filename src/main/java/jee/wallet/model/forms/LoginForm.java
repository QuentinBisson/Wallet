package jee.wallet.model.forms;

import jee.wallet.model.ejb.UserEjb;
import jee.wallet.model.entities.User;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LoginForm extends AbstractForm<User> {

    private static final String USERNAME_ATTR = "username";
    private static final String PASSWORD_ATTR = "password";

    @EJB
    private UserEjb userEjb;

    @Override
    public User validateForm(HttpServletRequest request) {
        String username = request.getParameter(USERNAME_ATTR);
        String password = request.getParameter(PASSWORD_ATTR);
        if (StringUtils.isNotBlank(username)) {
            request.setAttribute(USERNAME_ATTR, username);
        } else {
            setResult("Echec: Le login est invalide.");
            addError(USERNAME_ATTR, "Le formulaire soumis est invalide.");
            return null;
        }
        if (StringUtils.isNotBlank(password)) {
            request.setAttribute(PASSWORD_ATTR, password);
        } else {
            setResult("Echec: Le mot de passe est invalide.");
            addError(PASSWORD_ATTR, "Le formulaire soumis est invalide.");
            return null;
        }
        User user = null;
        try {
            user = userEjb.login(username, password);
        } catch (UnsupportedEncodingException e) {
            setResult("Echec: Un problème interne a eut lieu.");
        } catch (NoSuchAlgorithmException e) {
            setResult("Echec: Un problème interne a eut lieu.");
        }

        if (user == null) {
            setResult("Echec: Le couple login / mot de passe n'existe pas.");
        }
        return user;
    }
}
