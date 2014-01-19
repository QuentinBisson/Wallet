package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-01-19T18:03:23")
@StaticMetamodel(StockExchange.class)
public class StockExchange_ { 

    public static volatile SingularAttribute<StockExchange, Long> id;
    public static volatile SingularAttribute<StockExchange, String> name;
    public static volatile ListAttribute<StockExchange, Company> companies;
    public static volatile ListAttribute<StockExchange, StockOption> options;

}