package jee.wallet.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.OperationType;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2014-01-13T03:19:03")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Long> id;
    public static volatile ListAttribute<Transaction, StockOption> stockOptions;
    public static volatile SingularAttribute<Transaction, Double> price;
    public static volatile SingularAttribute<Transaction, OperationType> type;
    public static volatile SingularAttribute<Transaction, Date> transactionDate;

}