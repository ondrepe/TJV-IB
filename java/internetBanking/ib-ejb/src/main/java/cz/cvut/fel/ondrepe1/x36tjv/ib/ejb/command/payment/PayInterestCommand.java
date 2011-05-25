package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.payment;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GlobalParamCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.AccountTransferMoneyCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.TransferEnum;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class PayInterestCommand extends CommonCommand {

  private HashMap<String, BigDecimal> currencyRates;

  public PayInterestCommand(EntityManager em) {
    super(em);
    currencyRates = new HashMap<String, BigDecimal>();
  }

  public void payInterest() {
    GlobalParamCommand gp = new GlobalParamCommand(em);
    int myBankCode = gp.getNumberParam("MYBANKCODE");
    BankPO myBank = em.find(BankPO.class, myBankCode);
    
    List<AccountPO> list = getAccounts();
    
    for (AccountPO accPo : list) {
      CurrencyPO accCurrency = accPo.getCurrency();
      int dd = accCurrency.getDecimalDigits();
      BigDecimal balance = getLowDailyBalance(accPo);
      BigDecimal rate = getCurrencyRate(accCurrency);
      BigDecimal interest = balance.multiply(rate, new MathContext(24, RoundingMode.HALF_UP));
      
      BankTransactionPO trn = new BankTransactionPO();
      trn.setAccountNumberFrom("OndrepeBank Interest");
      trn.setBankFrom(myBank);
      trn.setAccountNumberTo(accPo.getAccountNumber());
      trn.setAccountTo(accPo);
      trn.setBankTo(myBank);
      trn.setAmountFrom(interest.negate().setScale(dd, RoundingMode.HALF_UP));
      trn.setAmountTo(interest.setScale(dd, RoundingMode.HALF_UP));
      trn.setCurrencyFrom(accCurrency);
      trn.setCurrencyTo(accCurrency);
      trn.setCreationTime(new Date());
      trn.setDescription("interest");
      
      AccountTransferMoneyCommand accCommand = new AccountTransferMoneyCommand(em);
      accCommand.transferMoney(accPo, interest, accCurrency, TransferEnum.INPUT, true);
      em.persist(trn);
    }
    updateCurrencyRates();
  }

  private List<AccountPO> getAccounts() {
    Query query = em.createNamedQuery("AccountPO.findByValid");
    query.setParameter("valid", "Y");
    return query.getResultList();
  }

  private BigDecimal getLowDailyBalance(AccountPO accPo) {
    return accPo.getLowestDailyBalance();
  }

  private BigDecimal getCurrencyRate(CurrencyPO curPo) {
    BigDecimal rate = null;
    String code = curPo.getCode();
    if (currencyRates.containsKey(code)) {
      rate = currencyRates.get(code);
    } else {
      CurrencyRatePO curRatePo = em.find(CurrencyRatePO.class, code);
      rate = curRatePo.getRate();
      rate = rate.movePointLeft(2);
      currencyRates.put(code, rate);
    }
    return rate;
  }

  private void updateCurrencyRates() {
    Query query = em.createNamedQuery("CurrentCurrencyRatePO.findAll");
    List<CurrentCurrencyRatePO> list = query.getResultList();
    
    for (CurrentCurrencyRatePO ccRatePo : list) {
      CurrencyRatePO cRatePo = em.find(CurrencyRatePO.class, ccRatePo.getCode());
      cRatePo.setRate(ccRatePo.getRate());
      em.persist(cRatePo);
    }
  }

  @Override
  protected boolean authorize() {
    return true;
  }
}
