package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Transaction;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-01-19T18:03:23")
@StaticMetamodel(Wallet.class)
public class Wallet_ { 

    public static volatile SingularAttribute<Wallet, Long> id;
    public static volatile SingularAttribute<Wallet, Double> balance;
    public static volatile ListAttribute<Wallet, Transaction> transactions;

}