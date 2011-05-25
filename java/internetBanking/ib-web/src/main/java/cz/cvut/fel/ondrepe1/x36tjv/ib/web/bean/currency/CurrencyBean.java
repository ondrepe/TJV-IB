package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.currency;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
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
public class CurrencyBean extends CommonADLBean<CurrencyCode> {

  @EJB
  protected ICurrencyCodeBean currencyBean;
  
  @Override
  protected List<CurrencyCode> load() {
    List<CurrencyCode> list = currencyBean.getList();
    setRenderDelete(!list.isEmpty());
    return list;
  }

  @Override
  protected void deleteItem() {
    currencyBean.delete(getSelectedItem());
  }

  @Override
  protected void addItem(CurrencyCode item) {
    currencyBean.set(item);
  }

  @Override
  protected CurrencyCode initItem() {
    return new CurrencyCode();
  }
}
