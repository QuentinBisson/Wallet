package jee.wallet.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.History;
import jee.wallet.model.entities.StockExchange;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2014-01-13T03:19:03")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, Long> id;
    public static volatile SingularAttribute<Company, String> sector;
    public static volatile SingularAttribute<Company, Double> marketCap;
    public static volatile ListAttribute<Company, History> history;
    public static volatile SingularAttribute<Company, Date> creationDate;
    public static volatile SingularAttribute<Company, Float> lastSale;
    public static volatile SingularAttribute<Company, String> summaryQuote;
    public static volatile SingularAttribute<Company, Long> adrTso;
    public static volatile SingularAttribute<Company, Integer> ipoYear;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile ListAttribute<Company, StockExchange> stockExchanges;
    public static volatile SingularAttribute<Company, String> code;
    public static volatile SingularAttribute<Company, String> industry;
    public static volatile ListAttribute<Company, StockOption> options;

}