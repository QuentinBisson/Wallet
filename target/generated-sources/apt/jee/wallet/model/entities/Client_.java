package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.ClientStatusType;
import jee.wallet.model.entities.ClientType;
import jee.wallet.model.entities.Wallet;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-01-19T18:03:23")
@StaticMetamodel(Client.class)
public class Client_ extends User_ {

    public static volatile SingularAttribute<Client, String> lastName;
    public static volatile SingularAttribute<Client, ClientStatusType> status;
    public static volatile SingularAttribute<Client, String> firstName;
    public static volatile SingularAttribute<Client, ClientType> type;
    public static volatile SingularAttribute<Client, Wallet> wallet;

}