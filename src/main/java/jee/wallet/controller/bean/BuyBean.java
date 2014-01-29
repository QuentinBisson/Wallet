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

    @EJB
    private CompanyEjb companyEjb;
    @EJB
    private ClientEjb clientEjb;

    public BuyBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("je passe la !!!!");
        System.out.println("facesContext.getExternalContext().getRequestParameterMap() " + facesContext.getExternalContext().getRequestParameterMap());
        id = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("id"));
        client = (Client) context.getSessionMap().get("user");
    }

    @PostConstruct
    public void init() {
        company = companyEjb.findById(id);
    }

    public void buyAction() {

        System.out.println(company.getName());
        clientEjb.buyStockOptions(client, company, numberOfActions, TransactionType.NORMAL);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous avez acheter "
                + numberOfActions
                + " pour l'entreprise "
                + company.getName(), company.getName() + " : " + numberOfActions + " actions");
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

}
