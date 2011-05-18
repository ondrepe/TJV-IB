package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client.ExchangeOfficeClient;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class GetAmountCommand extends CommonCommand {
  
  public GetAmountCommand(EntityManager em) {
    super(em);
  }
  
  public BigDecimal getAmount(AccountPO acc, CurrencyPO curr, BigDecimal amount, TransferEnum direction) throws CommonIBException {
    boolean isInput = TransferEnum.INPUT.equals(direction);
    BigDecimal amountResult = BigDecimal.ZERO;
    CurrencyPO accCur = acc.getCurrency();
    
    ExchangeOfficeClient eoClient = new ExchangeOfficeClient(em);
    BigDecimal rate = eoClient.getRate(curr, accCur);
    
    MathContext mCtx = new MathContext(24);
    BigDecimal amountInAccCurr = amount.multiply(rate, mCtx);
    
    if (isInput) {
      amountResult = amountResult.add(amountInAccCurr, mCtx);
    } else {
      amountResult = amountResult.subtract(amountInAccCurr, mCtx);
    }
    return amountResult.setScale(4, RoundingMode.HALF_UP);
  }
}
