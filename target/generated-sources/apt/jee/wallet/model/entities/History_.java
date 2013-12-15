package jee.wallet.model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jee.wallet.model.entities.Company;

@Generated(value="EclipseLink-2.4.0.v20120608-rNA", date="2013-12-15T14:59:07")
@StaticMetamodel(History.class)
public class History_ { 

    public static volatile SingularAttribute<History, Long> id;
    public static volatile SingularAttribute<History, Company> company;
    public static volatile SingularAttribute<History, Integer> version;

}