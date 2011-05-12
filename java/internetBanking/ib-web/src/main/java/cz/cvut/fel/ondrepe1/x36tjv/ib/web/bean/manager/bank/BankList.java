package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.ListAndDeleteBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class BankList extends ListAndDeleteBean<BankCode> {

  @Override
  protected List<BankCode> load() {
    ArrayList<BankCode> codes = new ArrayList<BankCode>();
    codes.add(new BankCode(123, "Komercka"));
    codes.add(new BankCode(456, "AirBank"));
    codes.add(new BankCode(789, "Raiffaisen"));
    return codes;
  }

  @Override
  public void delete() {
    System.out.println(getSelectedItem());
//    return null;
  }
}
