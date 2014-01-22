package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.UserEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientStatusType;
import jee.wallet.model.entities.User;
import org.apache.commons.lang.StringUtils;

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

    public LoginBean() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        if (externalContext.getSessionMap().containsKey("user")) {
            User user = (User) externalContext.getSessionMap().get("user");
            redirect(user);
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

    private void redirect(User user) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (user instanceof Administrator) {
            context.getExternalContext().redirect(REDIRECT_ADMIN_URL);
        } else if (user instanceof Client) {
            context.getExternalContext().redirect(REDIRECT_USER_URL);
        }
    }

    public void connect() throws IOException {
        boolean isValid = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if (StringUtils.isBlank(userName)) {
            isValid = false;
            context.addMessage("userName",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Le nom d'utilisateur ne peut être vide",
                            "Erreur de connection"));
        }

        if (StringUtils.isBlank(password)) {
            isValid = false;
            context.addMessage("password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Le mot de passe ne peut être vide",
                            "Erreur de connection"));
        }
        if (isValid) {
            User user = userEjb.login(userName, password);
            if (user != null) {
                if (!(user instanceof Client && ClientStatusType.CLOSED.equals(
                        ((Client) user).getStatus()))) {
                    ExternalContext externalContext = context.getExternalContext();
                    user.setLastConnection(new Date());
                    userEjb.update(user);
                    externalContext.getSessionMap().put("user", user);
                    redirect(user);
                }
            }
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("user");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Wallet");

        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
