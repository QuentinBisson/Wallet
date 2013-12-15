package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.StockOption;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(Wallet.class)
public class Wallet_ { 

    public static volatile SingularAttribute<Wallet, Long> id;
    public static volatile SetAttribute<Wallet, StockOption> stockOptions;
    public static volatile SingularAttribute<Wallet, Integer> version;

}