package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface IBankCodeBean {
  
  public boolean existBankCode(int code);
  public boolean existBankName(String name);
  public List<BankCode> getAll();
  public void delete(int code);
  public void set(BankCode bankCode);
}
