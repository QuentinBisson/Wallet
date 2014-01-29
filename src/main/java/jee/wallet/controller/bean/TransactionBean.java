package jee.wallet.controller.bean;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.ejb.CompanyEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.OperationType;
import jee.wallet.model.entities.Transaction;
import jee.wallet.model.entities.TransactionType;

/**
 *
 * @author Quentin
 */
public class TransactionBean {

    @EJB
    private ClientEjb clientEjb;

    @EJB
    private CompanyEjb companyEjb;

    private Client client;
    private long companyId;
    private int numberOfActions;

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (context.getSessionMap().containsKey("user")) {
            if (context.getSessionMap().get("user") instanceof Administrator) {
                try {
                    context.redirect(context.getRequestContextPath() + "index.xtml");
                } catch (IOException ex) {
                    Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            client = (Client) context.getSessionMap().get("user");
        } else {
            try {
                context.redirect(context.getRequestContextPath() + "index.xtml");
            } catch (IOException ex) {
                Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long getUserActionsInCompany(Company company) {
        long actions = 0;
        for (Transaction t : client.getWallet().getTransactions()) {
            if (t.getStockOptions().get(0).getCompany().equals(company)) {
                if (OperationType.PURCHASE == t.getOperationType()) {
                    actions += t.getStockOptions().size();
                } else {
                    actions -= t.getStockOptions().size();
                }
            }
        }
        return actions;
    }

    public int getNumberOfActions() {
        return numberOfActions;
    }

    public void setNumberOfActions(int value) {
        this.numberOfActions = value;
    }

    public void buyActions(Long id) {
        if (companyId < 0) {
            throw new IllegalArgumentException("id < 0");
        }
        System.out.println("id : "+id);
        Company company = companyEjb.findById(companyId);
        System.out.println(company.getName());
        clientEjb.buyStockOptions(client, company, numberOfActions, TransactionType.NORMAL);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous avez acheter "
                + numberOfActions
                + " pour l'entreprise "
                + company.getName(), company.getName() + " : " + numberOfActions + " actions");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void sellActions(ActionEvent event) {
        if (companyId < 0) {
            throw new IllegalArgumentException("id < 0");
        }
        System.out.println(companyId);
        Company company = companyEjb.findById(companyId);
        clientEjb.sellStockOptions(client, company, numberOfActions, TransactionType.NORMAL);
    }

    public void speculate(ActionEvent event) {
        if (companyId < 0) {
            throw new IllegalArgumentException("id < 0");
        }
        System.out.println(companyId);
        Company company = companyEjb.findById(companyId);
        clientEjb.sellStockOptions(client, company, numberOfActions, TransactionType.PRIVILEGED);
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

}
