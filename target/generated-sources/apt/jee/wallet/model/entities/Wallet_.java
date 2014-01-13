package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Transaction;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2014-01-13T03:19:03")
@StaticMetamodel(Wallet.class)
public class Wallet_ { 

    public static volatile SingularAttribute<Wallet, Long> id;
    public static volatile SingularAttribute<Wallet, Double> balance;
    public static volatile ListAttribute<Wallet, Transaction> transactions;

}