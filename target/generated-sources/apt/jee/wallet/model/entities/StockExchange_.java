package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(StockExchange.class)
public class StockExchange_ { 

    public static volatile SingularAttribute<StockExchange, Long> id;
    public static volatile SingularAttribute<StockExchange, String> name;
    public static volatile SetAttribute<StockExchange, Company> companies;
    public static volatile SingularAttribute<StockExchange, Integer> version;
    public static volatile SetAttribute<StockExchange, StockOption> options;

}