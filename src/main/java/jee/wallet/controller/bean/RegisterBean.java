/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.wallet.controller.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientStatusType;
import jee.wallet.model.entities.ClientType;
import jee.wallet.model.entities.Wallet;

/**
 *
 * @author David
 */
public class RegisterBean implements Serializable{

    private Client user;
    private String confirmPassword;
    @EJB
    private ClientEjb clientEjb;
    
    public RegisterBean(){
        user = new Client();
    }
    

    public void register() {
        clientEjb.create(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès de l'inscription !", ""));  
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
