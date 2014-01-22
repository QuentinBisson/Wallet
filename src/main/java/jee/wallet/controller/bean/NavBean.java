package jee.wallet.controller.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Quentin
 */
public class NavBean {
    private static final String HOME_PATH = "/";
    private static final String LOGIN_PATH = "/login.xhtml";
    private static final String REGISTER_PATH = "/register.xhtml";
    private static final String ADMIN_PATH = "/admin/admin.xhtml";
    private static final String WALLET_PATH = "/user/user.xhtml";
    
    public String getHomeLink() {
    	ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
    	return context.encodeActionURL(HOME_PATH);
    }
    
    public String getLoginLink() {
    	ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
    	return context.encodeActionURL(LOGIN_PATH);
    }
    
    public String getRegisterLink() {
    	ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
    	return context.encodeActionURL(REGISTER_PATH);
    }
    
    public String getWalletLink() {
    	ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
    	return context.encodeActionURL(WALLET_PATH);
    }
    
    public String getAdminLink() {
    	ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
    	return context.encodeActionURL(ADMIN_PATH);
    }
}
