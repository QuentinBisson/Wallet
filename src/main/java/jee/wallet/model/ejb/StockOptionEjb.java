package jee.wallet.model.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@RemoteBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StockOptionEjb extends AbstractEjb<StockOption>
{
}