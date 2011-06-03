package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyRateBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonADLBean;
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
public class CurrencyRateBean extends CommonADLBean<CurrencyRate> {

  @EJB
  private ICurrencyRateBean rateBean;
  @EJB
  private ICurrencyCodeBean codeBean;
  private List<CurrencyCode> currencyList;
  
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
    List<CurrencyRate> list = rateBean.getList();
    setRenderDelete(!list.isEmpty());
    currencyList = codeBean.getList();
    return list;
  }

  @Override
  protected void deleteItem() {
    rateBean.delete(getSelectedItem());
  }
  
  public List<CurrencyCode> getCurrencyList() {
    return currencyList;
  }
  
}
