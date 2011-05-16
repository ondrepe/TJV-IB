package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerAccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.Random;
import javax.persistence.EntityManager;

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
    Random rnd = new Random();
    CurrencyPO crPo = em.find(CurrencyPO.class, acc.getCurrencyCode());
    CustomerPO cPo = em.find(CustomerPO.class, acc.getCustomerId());
    
    AccountPO accPo = new AccountPO();
    accPo.setId(10000 + (rnd.nextInt() % 1000));
    accPo.setBalance(acc.getBalance());
    accPo.setAccountNumber("13-013441" + (rnd.nextInt() % 1000));
    accPo.setCode(crPo);
    accPo.setValid("Y");
    em.persist(accPo);
    
    CustomerAccountPO caPo = new CustomerAccountPO();
    caPo.setId(10000 + (rnd.nextInt() % 1000));
    caPo.setIdAccount(accPo);
    caPo.setIdCustomer(cPo);
    
    em.persist(caPo);
    
  }
  
}
