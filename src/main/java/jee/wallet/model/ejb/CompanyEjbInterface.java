package jee.wallet.model.ejb;

import java.util.List;
import jee.wallet.model.entities.Company;
import jee.wallet.model.entities.History;

public interface CompanyEjbInterface extends CrudInterface<Company> {

    public List<History> getRealTimeValue(Company company);
}
