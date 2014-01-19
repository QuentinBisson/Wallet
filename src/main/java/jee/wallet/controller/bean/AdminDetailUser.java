/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.wallet.controller.bean;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author David
 */
public class AdminDetailUser implements Serializable {

    private long id;
    
    public AdminDetailUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        id = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("id"));
        System.out.println("id : "+id);
    }
    
    public long getId(){
        return id;
    }
}
