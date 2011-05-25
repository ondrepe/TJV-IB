package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface ICurrencyCodeBean {
  
  public List<CurrencyCode> getList();
  public void delete(String code);
  public void set(CurrencyCode bankCode);
}
