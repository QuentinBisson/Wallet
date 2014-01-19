/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientType;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author David
 */
public class AdminBean implements Serializable {

    private List<Client> clients;

    @EJB
    private ClientEjb clientEjb;

    public AdminBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Administrator user = (Administrator) externalContext.getSessionMap().get("user");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Wallet/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @PostConstruct
    public void init() {
        clients = clientEjb.findAll(0, Integer.MAX_VALUE);
    }

    public void onEdit(RowEditEvent event) {
        Client c = ((Client) event.getObject());
        clientEjb.update(c);
        FacesMessage msg = new FacesMessage("Le client " + c.getUsername() + "à été mis à jour", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Mise à jour annulé", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public ClientType[] getTypeUser(){
        return ClientType.values();
    }

    public List<Client> getClients() {
        return clients;
    }
    
    public void showDetail(long id){
        try {
            System.out.println("id "+id);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Wallet/admin/adminDetailUser.xhtml?id="+id);
        } catch (IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
