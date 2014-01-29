package jee.wallet.controller.bean;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.ejb.CompanyEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.OperationType;
import jee.wallet.model.entities.Transaction;

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
    private List<Transaction> transactions;

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
            transactions = client.getWallet().getTransactions();
        } else {
            try {
                context.redirect(context.getRequestContextPath() + "index.xtml");
            } catch (IOException ex) {
                Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long getUserActionsInCompany(Object companyId) {
        if (companyId == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "L'entreprise n'existe pas", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        long actions = 0;
        for (Transaction t : transactions) {
            if (t.getStockOptions().get(0).getCompany().getId() == companyId) {
                if (OperationType.PURCHASE == t.getOperationType()) {
                    actions += t.getStockOptions().size();
                } else {
                    actions -= t.getStockOptions().size();
                }
            }
        }
        return actions;
    }

}
