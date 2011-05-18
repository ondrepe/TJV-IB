package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class AccountSetCommand extends CommonSetCommand<Account> {

  public AccountSetCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected void validate(Account object) throws CommonIBException {
    
  }

  @Override
  protected void execute(Account acc) {
    CurrencyPO crPo = em.find(CurrencyPO.class, acc.getCurrencyCode());
    CustomerPO cPo = em.find(CustomerPO.class, acc.getCustomerId());
    
    AccountPO accPo = new AccountPO();
    accPo.setBalance(acc.getBalance());
    accPo.setLowestDailyBalance(acc.getBalance());
    accPo.setAccountNumber(createAccountNumber());
    accPo.setCurrency(crPo);
    accPo.setValid("Y");
    accPo.getCustomers().add(cPo);
    em.persist(accPo);
  }
  
  protected String createAccountNumber() {
    Integer accNum = 100000010;
    Query query = em.createNamedQuery("AccountPO.getAccNums");
    List<String> accNumList = query.getResultList();
    if(!accNumList.isEmpty()) {
      Integer lastAccNum = Integer.parseInt(accNumList.get(0));
      accNum = lastAccNum + 11;
    }
    return accNum.toString();
  }
  
}
