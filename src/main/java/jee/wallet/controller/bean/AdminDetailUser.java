package jee.wallet.controller.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import jee.wallet.model.ejb.ClientEjb;
import jee.wallet.model.entities.Client;

/**
 *
 * @author David
 */
public class AdminDetailUser implements Serializable {

    private Client client;
    @EJB
    private ClientEjb clientEjb;
    private long id;

    public AdminDetailUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        id = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("id"));
    }

    @PostConstruct
    public void init() {
        client = clientEjb.findById(id);
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }
}
