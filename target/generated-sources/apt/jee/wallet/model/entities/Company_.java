package jee.wallet.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.History;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, Long> id;
    public static volatile SingularAttribute<Company, String> sector;
    public static volatile SetAttribute<Company, History> history;
    public static volatile SingularAttribute<Company, Date> creationDate;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, String> code;
    public static volatile SingularAttribute<Company, Integer> version;
    public static volatile SetAttribute<Company, StockOption> options;

}