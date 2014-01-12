package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import jee.wallet.model.ejb.UserEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.User;
import jee.wallet.model.forms.Form;
import jee.wallet.model.forms.LoginForm;

/**
 *
 * @author David
 */
@ManagedBean(name = "loginBean", eager = true)
@RequestScoped
public class LoginBean implements Serializable {

    private static final String REQUEST_NOTICE = "notice";
    private static final String REQUEST_FORM = "form";
    private static final String REDIRECT_ADMIN_URL = "/Wallet/admin.xhtml";
    private static final String REDIRECT_USER_URL = "Wallet/wallet.xhtml";
    private static final String LOGIN_URL = "/login.xhtml";
    private static final String USER_ATTR = "user";

    @NotNull
    private String userName;
    @NotNull
    private String password;

    @EJB
    private UserEjb userEjb;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void connect() throws IOException {
        System.out.println(userName + " " + password);
        try {
            User user = userEjb.login(userName, password);
            System.out.println("USER " + user);
            if (user != null) {
                user.setLastConnection(new Date());
                userEjb.update(user);
                if (user instanceof Administrator) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(REDIRECT_ADMIN_URL);
                } else if (user instanceof Client) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(REDIRECT_USER_URL);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Oups");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("oups");
        }
    }
}
