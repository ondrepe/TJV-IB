package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface IBankCodeBean {
  
  public List<BankCode> getAll();
  public void delete(int code) throws CommonIBException;
  public void set(BankCode bankCode) throws CommonIBException;
}
