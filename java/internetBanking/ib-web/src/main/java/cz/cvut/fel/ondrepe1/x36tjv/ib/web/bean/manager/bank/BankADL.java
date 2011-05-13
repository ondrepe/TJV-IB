package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
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
public class BankADL extends ADLBean<BankCode> {

  @EJB
  protected IBankCodeBean bankBean;
  
  @Override
  protected List<BankCode> load() {
    return bankBean.getAll();
  }

  @Override
  protected void deleteItem() throws CommonIBException {
    Integer code = Integer.parseInt(this.getSelectedItem());
    bankBean.delete(code);
  }

  @Override
  protected void addItem(BankCode item) throws CommonIBException {
    bankBean.set(item);
  }

  @Override
  protected BankCode initItem() {
    return new BankCode();
  }
}
