package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockExchange;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-01-19T18:03:23")
@StaticMetamodel(StockOption.class)
public class StockOption_ { 

    public static volatile SingularAttribute<StockOption, Long> id;
    public static volatile SingularAttribute<StockOption, Company> company;
    public static volatile SingularAttribute<StockOption, StockExchange> stockExchange;

}