package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface IAuthentizationBean {
  
  public Customer getLoggedCustomer();
}
