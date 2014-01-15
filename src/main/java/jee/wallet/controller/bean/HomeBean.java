package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.StockExchangeEjb;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockExchange;

@ManagedBean(name = "homeBean")
@SessionScoped
public class HomeBean implements Serializable {

    @EJB
    private StockExchangeEjb exchangeEjb;
    //private Map<StockExchange, LazyDataModel<Company>> companies;
    private List<StockExchange> exchanges;
    private long selectedExchange;
    private Map<String,LazyExchangeModel> companies;
    
    public HomeBean(){
        System.out.println("test");
        companies = new HashMap<String, LazyExchangeModel>();
    }
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
        for(StockExchange se : exchanges){
            companies.put(se.getName(), new LazyExchangeModel(se.getCompanies()));
        }
    }
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
        if (exchanges == null) {
            exchanges = exchangeEjb.findAll(0, Integer.MAX_VALUE);
        }
        return exchanges;
    }

    public long getSelectedExchange() {
        return selectedExchange;
    }

    public void setSelectedExchange(long selectedExchange) {
        this.selectedExchange = selectedExchange;
    }

    public Map<String, LazyExchangeModel> getCompanies() {
        return companies;
    }


}
