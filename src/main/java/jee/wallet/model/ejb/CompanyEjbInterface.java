package jee.wallet.model.ejb;

import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.History;

public interface CompanyEjbInterface extends CrudInterface<Company> {

    public History getRealTimeValue(Company company);
}
