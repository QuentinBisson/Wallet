package jee.wallet.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Company;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2014-01-13T03:19:03")
@StaticMetamodel(History.class)
public class History_ { 

    public static volatile SingularAttribute<History, Long> id;
    public static volatile SingularAttribute<History, Float> open;
    public static volatile SingularAttribute<History, Float> adjClose;
    public static volatile SingularAttribute<History, Company> company;
    public static volatile SingularAttribute<History, Long> volume;
    public static volatile SingularAttribute<History, Float> high;
    public static volatile SingularAttribute<History, Date> date;
    public static volatile SingularAttribute<History, Float> low;
    public static volatile SingularAttribute<History, Float> close;

}