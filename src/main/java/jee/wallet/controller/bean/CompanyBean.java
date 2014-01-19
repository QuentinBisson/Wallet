package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.CompanyEjb;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.History;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Quentin
 */
public class CompanyBean implements Serializable {
    
    @EJB
    private CompanyEjb companyEjb;
    private Company company;
    private CartesianChartModel historyChartModel;
    private final static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
    
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = context.getRequestParameterMap();
        company = companyEjb.findById(new Integer(params.get("id")));
        if (!FacesContext.getCurrentInstance()
                .getPartialViewContext().isAjaxRequest()) {
            try {
                if (company == null) {
                    context.redirect(context.getRequestContextPath() + "index.xtml");
                }
                companyEjb.realTimeUpdate(company);
            } catch (IOException ex1) {
                Logger.getLogger(HomeBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public List<History> getHistory() {
        return company.getHistory();
    }
    
    public Company getCompany() {
        return company;
    }
    
    public CartesianChartModel getHistoryChartModel() {
        historyChartModel = new CartesianChartModel();
        ChartSeries companyHistorySeries = new ChartSeries();
        companyHistorySeries.setLabel("Valeur");
        List<History> history = company.getHistory();
        for (int i=50; i>0; i--) {
            History h = history.get(i);
            companyHistorySeries.set(dateFormat.format(h.getDate()), h.getClose());
        }
        
        historyChartModel.addSeries(companyHistorySeries);
        return historyChartModel;
    }
    
}
