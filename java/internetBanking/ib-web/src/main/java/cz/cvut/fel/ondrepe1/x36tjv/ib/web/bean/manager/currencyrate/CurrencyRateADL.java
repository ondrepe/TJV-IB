package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyRateBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.ADLBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class CurrencyRateADL extends ADLBean<CurrencyRate> {

  @EJB
  private ICurrencyRateBean rateBean;
  @EJB
  private ICurrencyCodeBean codeBean;
  
  @Override
  protected void addItem(CurrencyRate item) {
    rateBean.set(item);
  }

  @Override
  protected CurrencyRate initItem() {
    return new CurrencyRate();
  }

  @Override
  protected List<CurrencyRate> load() {
    return rateBean.getAll();
  }

  @Override
  protected void deleteItem() {
    rateBean.delete(getSelectedItem());
  }
  
  public List<CurrencyCode> getCurrencyList() {
    return codeBean.getAll();
  }
  
}
