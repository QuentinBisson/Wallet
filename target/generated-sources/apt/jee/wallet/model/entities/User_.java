package jee.wallet.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2014-01-12T18:57:22")
@StaticMetamodel(User.class)
public abstract class User_ { 

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, Date> lastConnection;
    public static volatile SingularAttribute<User, String> password;

}