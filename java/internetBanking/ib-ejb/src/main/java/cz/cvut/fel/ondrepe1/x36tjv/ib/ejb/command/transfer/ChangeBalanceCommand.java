package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client.ExchangeOfficeClient;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class ChangeBalanceCommand extends CommonCommand {
  
  public ChangeBalanceCommand(EntityManager em) {
    super(em);
  }
  
  public BigDecimal changeBalance(AccountPO acc, CurrencyPO curr, BigDecimal amount, ChangeBalanceEnum direction) {
    boolean isInput = ChangeBalanceEnum.INPUT.equals(direction);
    BigDecimal balance = BigDecimal.ZERO;
    CurrencyPO accCur = acc.getCurrency();
    
    ExchangeOfficeClient eoClient = new ExchangeOfficeClient();
    BigDecimal rate = eoClient.getRate(curr, accCur);
    
    MathContext mCtx = new MathContext(4);
    BigDecimal amountInAccCurr = amount.multiply(rate, mCtx);
    mCtx = new MathContext(accCur.getDecimalDigits());
    
    if (isInput) {
      balance = balance.add(amountInAccCurr, mCtx);
    } else {
      balance = balance.subtract(amountInAccCurr, mCtx);
    }
    return balance;
  }
}
