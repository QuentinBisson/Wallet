package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import jee.wallet.model.ejb.StockExchangeEjb;
import jee.wallet.model.entities.StockExchange;

@ManagedBean(name = "homeBean", eager = true)
@ViewScoped
public class HomeBean implements Serializable {

    @EJB
    private StockExchangeEjb exchangeEjb;
    //private Map<StockExchange, LazyDataModel<Company>> companies;
    private List<StockExchange> exchanges;
    private long selectedExchange;

    
    /*public void init() {
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
     }*/

    /*public Map<StockExchange, LazyDataModel<Company>> getCompanies() {
     return companies;
     }*/
    
    public List<StockExchange> getExchanges() {
        if(exchanges == null){
            exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);;
        }
        return exchanges;
    }

    public long getSelectedExchange() {
        return selectedExchange;
    }

    public void setSelectedExchange(long selectedExchange) {
        this.selectedExchange = selectedExchange;
    }

    public LazyExchangeModel getCompanies() {
        StockExchange ex = exchangeEjb.findById(selectedExchange);
        if (ex != null) {
            try {
                exchangeEjb.realTimeUpdate(ex);
            } catch (IOException ex1) {
                Logger.getLogger(HomeBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return new LazyExchangeModel(ex.getCompanies());
        }
        return null;
    }

}
