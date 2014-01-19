package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
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

/**
 *
 * @author Quentin
 */
public class CompanyBean implements Serializable {

    @EJB
    private CompanyEjb companyEjb;
    private Company company;

    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String,String> params = context.getRequestParameterMap();
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
}
