package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.LBean;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ondrepe
 */
public class AccountV extends LBean<Account> {

  @EJB
  private IAccountBean accountBean;
  
  @Override
  protected List<Account> load() {
    return accountBean.getList();
  }
  
}
