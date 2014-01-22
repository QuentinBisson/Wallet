package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientStatusType;

public class UserBean implements Serializable {

    private double balance;

    private Client user;

    @EJB
    private ClientEjb clientEjb;

    private static final String HOME_PATH = "/";
    private static final String LOGIN_PATH = "/login.xhtml";
    
    public UserBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        user = (Client) externalContext.getSessionMap().get("user");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(
                    FacesContext.getCurrentInstance().getExternalContext()
                            .encodeActionURL(LOGIN_PATH));
            } catch (IOException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void creditListener() {
        user.getWallet().setBalance(user.getWallet().getBalance() + balance);
        clientEjb.update(user);
    }

    public void debitListener() {
        user.getWallet().setBalance(user.getWallet().getBalance() - balance);
        clientEjb.update(user);
    }

    public void closeAccount() {
        user.setStatus(ClientStatusType.CLOSED);
        clientEjb.update(user);
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("user");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    FacesContext.getCurrentInstance().getExternalContext()
                            .encodeActionURL(HOME_PATH));
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double value) {
        this.balance = value;
    }

}
