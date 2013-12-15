package jee.wallet.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, Date> lastConnection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> version;
    public static volatile SingularAttribute<User, String> salt;

}