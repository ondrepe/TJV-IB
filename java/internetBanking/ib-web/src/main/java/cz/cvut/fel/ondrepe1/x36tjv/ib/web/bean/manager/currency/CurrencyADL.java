package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.currency;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
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
public class CurrencyADL extends ADLBean<CurrencyCode> {

  @EJB
  protected ICurrencyCodeBean currencyBean;
  
  @Override
  protected List<CurrencyCode> load() {
    return currencyBean.getAll();
  }

  @Override
  protected void deleteItem() throws CommonIBException{
    currencyBean.delete(getSelectedItem());
  }

  @Override
  protected void addItem(CurrencyCode item) throws CommonIBException {
    currencyBean.set(item);
  }

  @Override
  protected CurrencyCode initItem() {
    return new CurrencyCode();
  }
}
