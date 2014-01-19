package jee.wallet.model.ejb;

import java.io.IOException;
import java.net.MalformedURLException;
import jee.wallet.model.entities.Company;

public interface CompanyEjbInterface extends CrudInterface<Company> {

    public void realTimeUpdate(Company company)
            throws MalformedURLException, IOException;
}
