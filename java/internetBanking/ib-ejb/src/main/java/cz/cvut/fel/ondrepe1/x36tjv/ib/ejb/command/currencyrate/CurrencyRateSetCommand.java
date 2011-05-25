package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import java.math.BigDecimal;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CurrencyRateSetCommand extends SetCommand<CurrencyRate> {

  public CurrencyRateSetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void validate(CurrencyRate object) {
    //validace objektu
    String code = object.getCode().trim().toUpperCase();
    BigDecimal rate = object.getRate();

    if (code.isEmpty() || code.length() > 3) {
      throw new IBException("Currency rate code must have 3 characters!", IBExceptionCode.VALIDATION_FAILED);
    }

    object.setCode(code);

    //validace jestli uz existuje
    Query query = em.createNamedQuery("CurrencyRatePO.findByCodeAndRate");
    query.setParameter("code", object.getCode());
    query.setParameter("rate", object.getRate());
    try {
      CurrencyRatePO cRate = (CurrencyRatePO) query.getSingleResult();
      if (cRate != null) {
        throw new IBException("CurrencyRate exist", IBExceptionCode.VALIDATION_FAILED);
      }
    } catch (PersistenceException ex) {
    }
  }

  @Override
  protected void set(CurrencyRate cr) {
    CurrentCurrencyRatePO ccRate = em.find(CurrentCurrencyRatePO.class, cr.getCode());
    CurrencyRatePO cRate;
    String code = cr.getCode().trim().toUpperCase();
    BigDecimal rate = cr.getRate();
    if (ccRate == null) {
      ccRate = new CurrentCurrencyRatePO(code, rate);
      cRate = new CurrencyRatePO(code, rate);
      em.persist(cRate);
    } else {
      ccRate.setRate(rate);
    }
    em.persist(ccRate);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
}
