package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class AccountSetCommand extends SetCommand<Account> {

  public AccountSetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void validate(Account object) {
    BigDecimal balance = object.getBalance();
    String currencyCode = object.getCurrencyCode();
    CurrencyPO currency = em.find(CurrencyPO.class, currencyCode);
    if(currency == null) {
      throw new IBException("Curency does not exist!", IBExceptionCode.VALIDATION_FAILED);
    }
    int dd = currency.getDecimalDigits();
    BigDecimal roundedBalance = balance.setScale(dd, RoundingMode.HALF_UP);
    int compare = roundedBalance.compareTo(BigDecimal.ZERO); 
    if(compare <= 0) {
      throw new IBException("Amount is less or equal zero!", IBExceptionCode.VALIDATION_FAILED);
    }
  }

  protected String createAccountNumber() {
    Integer accNum = 100000010;
    Query query = em.createNamedQuery("AccountPO.getAccNums");
    List<String> accNumList = query.getResultList();
    if (!accNumList.isEmpty()) {
      Integer lastAccNum = Integer.parseInt(accNumList.get(0));
      accNum = lastAccNum + 11;
    }
    return accNum.toString();
  }

  @Override
  protected void set(Account acc) {
    AccountPO accPo = convert(acc);
    em.persist(accPo);
  }

  protected AccountPO convert(Account acc) {
    AccountPO accPo = new AccountPO();
    
    CurrencyPO crPo = em.find(CurrencyPO.class, acc.getCurrencyCode());
    CustomerPO cPo = em.find(CustomerPO.class, acc.getCustomerId());

    accPo.setId(null);
    accPo.setBalance(acc.getBalance());
    accPo.setLowestDailyBalance(acc.getBalance());
    accPo.setAccountNumber(createAccountNumber());
    accPo.setCurrency(crPo);
    accPo.setValid("Y");
    accPo.getCustomers().add(cPo);

    return accPo;
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
}
