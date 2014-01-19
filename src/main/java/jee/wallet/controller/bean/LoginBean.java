package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.UserEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.User;

/**
 *
 * @author David
 */
public class LoginBean implements Serializable {

    private static final String REDIRECT_ADMIN_URL = "/Wallet/admin/admin.xhtml";
    private static final String REDIRECT_USER_URL = "/Wallet/user/user.xhtml";

    private String userName;
    private String password;
    @EJB
    private UserEjb userEjb;

    public LoginBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        if (externalContext.getSessionMap().containsKey("user")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(REDIRECT_ADMIN_URL);
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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

    public void connect() {
        try {
            User user = userEjb.login(userName, password);
            if (user != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                user.setLastConnection(new Date());
                userEjb.update(user);
                externalContext.getSessionMap().put("user", user);
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
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("after Valid");
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("user");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Wallet");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
