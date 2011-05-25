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
  
  public List<BankCode> getList();
  public List<BankCode> getListWithoutMyBank();
  public void delete(int code);
  public void set(BankCode bankCode);
}
