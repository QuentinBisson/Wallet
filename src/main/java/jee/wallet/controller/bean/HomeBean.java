package jee.wallet.controller.bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.StockExchangeEjb;
import jee.wallet.model.entities.StockExchange;

public class HomeBean implements Serializable {

    private static final String COMPANY_INDEX_PATH = "/Wallet/company/index.xhtml";
    private static final String COMPANY_BUY_PATH = "/Wallet/company/buy.xhtml";
    private static final String TRANSACTION_INDEX_PATH = "/Wallet/user/transactions.xhtml";

    @EJB
    private StockExchangeEjb exchangeEjb;

    private List<StockExchange> exchanges;

    public void init() {
        if (!FacesContext.getCurrentInstance()
                .getPartialViewContext().isAjaxRequest()) {
        }

        exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);
    }

    public List<StockExchange> getExchanges() {
        if (exchanges == null) {
            exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);
        }
        return exchanges;
    }

    public String getCompanyLink() {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        return context.encodeActionURL(COMPANY_INDEX_PATH);
    }
    
    public String getBuyAction() {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        return context.encodeActionURL(COMPANY_BUY_PATH);
    }

    public String getTransactionLink() {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        return context.encodeActionURL(TRANSACTION_INDEX_PATH);
    }
}
