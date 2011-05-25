package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class AccountTransferMoneyCommand extends CommonCommand {

  public AccountTransferMoneyCommand(EntityManager em) {
    super(em);
  }
  
  public BigDecimal transferMoney(AccountPO account, BigDecimal amount, CurrencyPO currency, TransferEnum direction) {
    return transferMoney(account, amount, currency, direction, false);
  }
  
  public BigDecimal transferMoney(AccountPO account, BigDecimal amount, CurrencyPO currency, TransferEnum direction, boolean resetLowestDailyBalance) {
    GetAmountCommand command = new GetAmountCommand(em);
    BigDecimal convertedAmount = command.getAmount(account, currency, amount, direction);
    BigDecimal newBalance = account.getBalance().add(convertedAmount, new MathContext(24, RoundingMode.HALF_UP));
    newBalance = newBalance.setScale(account.getCurrency().getDecimalDigits(), RoundingMode.HALF_UP);
    
    if(newBalance.doubleValue() < 0) {
      throw new IBException("Account does not have enough money!", IBExceptionCode.VALIDATION_FAILED);
    }
    if(newBalance.doubleValue() < account.getLowestDailyBalance().doubleValue() || resetLowestDailyBalance) {
      account.setLowestDailyBalance(newBalance);
    }
    account.setBalance(newBalance);
    em.persist(account);
    return convertedAmount;
  }

  @Override
  protected boolean authorize() {
    return true;
  }
  
}
