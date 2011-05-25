package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.AccountTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class AccountListByIdCommand extends ListByIdCommand<AccountPO, Account> {

  public AccountListByIdCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<AccountPO> list(int id) {
    CustomerPO cPo;
    if (isManager()) {
      cPo = em.find(CustomerPO.class, id);
    } else {
      cPo = getCustomer();
    }
    return cPo.getAccounts();
  }

  @Override
  protected List<Account> convert(List<AccountPO> listPo) {
    AccountTranslator translator = new AccountTranslator();
    return translator.translateList(listPo);
  }

  @Override
  protected boolean authorize() {
    return isManager() || isCustomer();
  }
}
