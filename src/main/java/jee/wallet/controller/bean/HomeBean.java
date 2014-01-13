package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.StockExchangeEjb;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockExchange;
import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "homeBean", eager = true)
@ViewScoped
public class HomeBean implements Serializable {

    @EJB
    private StockExchangeEjb exchangeEjb;
    private Map<StockExchange, LazyDataModel<Company>> companies;
    private List<StockExchange> exchanges;

    public void init() {
        if (!FacesContext.getCurrentInstance().
                getPartialViewContext().isAjaxRequest()) {  
            try {   
                exchangeEjb.realTimeUpdate();
            } catch (IOException ex) {
                Logger.getLogger(HomeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);
        companies = new WeakHashMap<StockExchange, LazyDataModel<Company>>();
        for (StockExchange se : exchanges) {
            companies.put(se, new LazyExchangeModel(se.getCompanies()));
        }
    }

    public Map<StockExchange, LazyDataModel<Company>> getCompanies() {
        return companies;
    }

    public List<StockExchange> getExchanges() {
        return exchanges;
    }
    
    
   
}
