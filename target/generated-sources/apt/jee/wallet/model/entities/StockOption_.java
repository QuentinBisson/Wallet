package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(StockOption.class)
public class StockOption_ { 

    public static volatile SingularAttribute<StockOption, Long> id;
    public static volatile SingularAttribute<StockOption, Company> company;
    public static volatile SingularAttribute<StockOption, Integer> version;
    public static volatile SingularAttribute<StockOption, StockOption> options;

}