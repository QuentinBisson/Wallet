package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.ejb.TransactionEjb;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.ClientStatusType;
import jee.wallet.model.entities.DisplayableTransaction;
import jee.wallet.model.entities.Transaction;
import jee.wallet.model.entities.TransactionType;

public class UserBean implements Serializable {

    private double balance;
    private Client user;

    @EJB
    private ClientEjb clientEjb;
    
    @EJB
    private TransactionEjb transactionEjb;

    private static final String HOME_PATH = "/";
    private static final String LOGIN_PATH = "/login.xhtml";

    private double total;

    public UserBean() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        total = 0;
        user = (Client) externalContext.getSessionMap().get("user");
        if (user == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    FacesContext.getCurrentInstance().getExternalContext()
                    .encodeActionURL(LOGIN_PATH));
        }
        for (Transaction t : user.getWallet().getTransactions()) {
            total += TransactionEjb.computeTransactionValue(t);
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

    public void cancelTransactionListener(long transactionId) {
        Transaction t = transactionEjb.findById(transactionId);
        clientEjb.cancelPrivilegedTransaction(user, t);
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
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buyPrivilegedActionsListener(long transactionId) {
        Transaction t = transactionEjb.findById(transactionId);
        clientEjb.buyStockOptions(user,
                t.getStockOptions().get(0).getCompany(),
                t.getStockOptions().size(),
                t.getTransactionType());
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double value) {
        this.balance = value;
    }

    public double getTotal() {
        return total;
    }

    public List<DisplayableTransaction> getTransactions() {
        List<DisplayableTransaction> transactions = new ArrayList<DisplayableTransaction>();
        for (DisplayableTransaction dt : user.getWallet().getDisplayableTransactions()) {
            if (dt.getType() == TransactionType.NORMAL) {
                transactions.add(dt);
            }
        }
        return transactions;
    }
    
    public List<DisplayableTransaction> getSpeculations() {
        List<DisplayableTransaction> transactions = new ArrayList<DisplayableTransaction>();
        for (DisplayableTransaction dt : user.getWallet().getDisplayableTransactions()) {
            if (dt.getType() == TransactionType.PRIVILEGED) {
                transactions.add(dt);
            }
        }
        return transactions;
    }

}
