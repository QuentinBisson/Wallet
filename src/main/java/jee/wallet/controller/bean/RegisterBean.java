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

/**
 *
 * @author David
 */
public class RegisterBean implements Serializable {

    private Client user;
    private String confirmPassword;
    @EJB
    private ClientEjb clientEjb;

    public RegisterBean() {
        user = new Client();
    }

    public void register() {
        if (validate()) {
            clientEjb.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès de l'inscription !", ""));
        }

    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    private boolean validate() {
        boolean valid = true;
        if (!user.getConfirmationPassword().equals(user.getPassword())) {
            valid = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de validation", "Les mots de passe ne sont pas identique"));
        }
        if (user.getUsername().isEmpty()) {
            valid = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de validation", "L'identifiant ne peut être null"));
        }
        if (user.getPassword().isEmpty()) {
            valid = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de validation", "Le mot de passe ne peut être null"));
        }

        return valid;
    }
}
