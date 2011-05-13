package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface ICurrencyRateBean {
  
  public List<CurrencyRate> getAll();
  public void delete(String code) throws CommonIBException;
  public void set(CurrencyRate bankCode) throws CommonIBException;
}
