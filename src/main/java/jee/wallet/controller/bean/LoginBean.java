package jee.wallet.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;  
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import jee.wallet.model.ejb.UserEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.User;
/**
 *
 * @author David
 */
@ManagedBean(name = "loginBean", eager = true)
@RequestScoped
public class LoginBean implements Serializable {
    
    private static final String REQUEST_NOTICE = "notice";
    private static final String REDIRECT_ADMIN_URL = "/admin.xhtml";
    private static final String REDIRECT_USER_URL = "/wallet.xhtml";
    private static final String LOGIN_URL = "/login.xhtml";
    private static final String USER_ATTR = "user";
    
    @NotNull
    private String userName;
    @NotNull
    private String password;

    @EJB
    private UserEjb userEjb;
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void connect() throws IOException {
        //Logger.getLogger(this.getClass().getName()).log(Level.INFO, "ici{0} {1}", new Object[]{userName, password});
    
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();

        Logger.getLogger(this.getClass().getName()).info("before login");
        User user = null;
        try {
            user = userEjb.login(userName, password);
        } catch (UnsupportedEncodingException e) {
            
        } catch (NoSuchAlgorithmException e) {
            
        }

        Logger.getLogger(this.getClass().getName()).info("after login");
 
        System.out.println(user);

        Logger.getLogger(this.getClass().getName()).info("before valid");
        if (user != null) {
            HttpSession session = request.getSession(true);
            user.setLastConnection(new Date());
            userEjb.update(user);
            request.setAttribute(REQUEST_NOTICE, "Vous êtes connecté en tant que " + user.getUsername());
            
            session.setAttribute(USER_ATTR, user);
            if (user instanceof Administrator) {
                response.sendRedirect(request.getContextPath() + REDIRECT_ADMIN_URL);
            } else if (user instanceof Client) {
                response.sendRedirect(request.getContextPath() + REDIRECT_USER_URL);
            }
        } else {
            response.sendRedirect(request.getContextPath() + LOGIN_URL);
        }
        System.out.println("after Valid");
    }
}
