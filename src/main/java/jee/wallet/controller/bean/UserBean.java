/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author David
 */
public class UserBean implements Serializable{

    private double balance;

    private Client user;

    @EJB
    private ClientEjb clientEjb;

    public UserBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        user = (Client) externalContext.getSessionMap().get("user");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Wallet/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void creditListener() {
        System.out.println("credit");
        user.getWallet().setBalance(user.getWallet().getBalance() + balance);
        clientEjb.update(user);
    }

    public void debitListener() {
        System.out.println("debit");
        user.getWallet().setBalance(user.getWallet().getBalance() - balance);
        clientEjb.update(user);
    }
    
    public void closeAccount(){
        clientEjb.delete(user);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("user");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Wallet");
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
