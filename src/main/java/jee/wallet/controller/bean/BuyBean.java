/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.wallet.controller.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.ejb.CompanyEjb;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientType;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.TransactionType;

/**
 *
 * @author Administrateur
 */
public class BuyBean {

    private long id;
    private Company company;
    private Client client;
    private int numberOfActions;

    private boolean sell;
    private boolean buy;
    private boolean speculate;

    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private ClientEjb clientEjb;

    public BuyBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        id = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("id"));
        sell = Boolean.parseBoolean(facesContext.getExternalContext().getRequestParameterMap().get("sell"));
        buy = Boolean.parseBoolean(facesContext.getExternalContext().getRequestParameterMap().get("buy"));
        client = (Client) context.getSessionMap().get("user");
        if (client.getType() == ClientType.PRIVILEGED) {
            speculate = Boolean.parseBoolean(facesContext.getExternalContext().getRequestParameterMap().get("speculate"));
        } else {
            speculate = true;
        }
    }

    @PostConstruct
    public void init() {
        company = companyEjb.findById(id);
    }

    public void buyAction() {
        FacesMessage msg = null;
        try {
            clientEjb.buyStockOptions(client, company, numberOfActions, TransactionType.NORMAL);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous avez acheter "
                    + numberOfActions
                    + " actions de l'entreprise "
                    + company.getName(), "");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solde du compte insuffisante", "");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void sellAction() {
        FacesMessage msg = null;
        try {
            clientEjb.sellStockOptions(client, company, numberOfActions, TransactionType.NORMAL);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous avez vendu "
                    + numberOfActions
                    + " actions de l'entreprise "
                    + company.getName(), "");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la vente", "");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
      public void SpeculateAction() {
        FacesMessage msg = null;
        try {
            clientEjb.sellStockOptions(client, company, numberOfActions, TransactionType.PRIVILEGED);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous avez spéculé "
                    + numberOfActions
                    + " actions de l'entreprise "
                    + company.getName(), "");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la speculation", "");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int getNumberOfActions() {
        return numberOfActions;
    }

    public void setNumberOfActions(int numberOfActions) {
        this.numberOfActions = numberOfActions;
    }

    public Company getCompany() {
        return company;
    }

    public Client getClient() {
        return client;
    }

    public boolean isSell() {
        return sell;
    }

    public boolean isBuy() {
        return buy;
    }

    public boolean isSpeculate() {
        return speculate;
    }

}
