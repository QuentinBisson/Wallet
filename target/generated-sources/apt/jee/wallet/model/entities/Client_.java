package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Wallet;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> lastName;
    public static volatile SingularAttribute<Client, String> firstName;
    public static volatile SingularAttribute<Client, Wallet> wallet;
    public static volatile SingularAttribute<Client, Integer> version;

}