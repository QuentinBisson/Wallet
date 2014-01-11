/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jee.wallet.wallet;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;  
import javax.inject.Named;  
import javax.validation.constraints.NotNull;
  

/**
 *
 * @author David
 */
@Named  
@SessionScoped 
public class LoginBean implements Serializable{
    
    @NotNull
    private String userName;
    @NotNull
    private String password;

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
    
    public void connect(){
        System.out.println(userName+" "+password);
    }
}
