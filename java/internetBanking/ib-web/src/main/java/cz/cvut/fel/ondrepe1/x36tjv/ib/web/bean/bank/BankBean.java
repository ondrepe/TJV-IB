package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
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
public class BankBean extends CommonADLBean<BankCode> {

  @EJB
  protected IBankCodeBean bankBean;
  private List<BankCode> listWithoutMyBank;

  @Override
  protected List<BankCode> load() {
    listWithoutMyBank = bankBean.getListWithoutMyBank();
    setRenderDelete(!listWithoutMyBank.isEmpty());
    return bankBean.getList();
  }

  @Override
  protected void deleteItem() {
    Integer code = Integer.parseInt(this.getSelectedItem());
    bankBean.delete(code);
  }

  @Override
  protected void addItem(BankCode item) {
    bankBean.set(item);
  }

  @Override
  protected BankCode initItem() {
    return new BankCode();
  }

  public List<BankCode> getListWithoutMyBank() {
    return listWithoutMyBank;
  }
}
