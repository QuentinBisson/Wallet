package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.StockExchangeEjb;
import jee.wallet.model.entities.StockExchange;

public class HomeBean implements Serializable {

    @EJB
    private StockExchangeEjb exchangeEjb;
    private List<StockExchange> exchanges;
    
    public void init() {
        if (!FacesContext.getCurrentInstance()
                .getPartialViewContext().isAjaxRequest()) {
            try {
                exchangeEjb.realTimeUpdate();
            } catch (IOException ex1) {
                Logger.getLogger(HomeBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);
    }

    public List<StockExchange> getExchanges() {
        if (exchanges == null) {
            exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);
        }
        return exchanges;
    }
}
