package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.EntityExistIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.ValidationIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CurrencyRateSetCommand extends CommonSetCommand<CurrencyRate> {

  public CurrencyRateSetCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected void validate(CurrencyRate object) throws CommonIBException {
    //validace objektu
    String code = object.getCode().trim().toUpperCase();
    BigDecimal rate = object.getRate();

    if (code.isEmpty() || code.length() > 3) {
      ValidationIBException ex = new ValidationIBException("Currency rate code must have 3 characters!");
      ex.setErrorAttribute("code");
      ex.setErrorValue(object.getCode());
    }

    object.setCode(code);

    //validace jestli uz existuje
    Query query = em.createNamedQuery("CurrencyRatePO.findByCodeAndRate");
    query.setParameter("code", object.getCode());
    query.setParameter("rate", object.getRate());
    try {
      CurrencyRatePO cRate = (CurrencyRatePO) query.getSingleResult();
      if (cRate != null) {
        throw new EntityExistIBException("CurrencyRate");
      }
    } catch (PersistenceException ex) {}
  }

  @Override
  protected void execute(CurrencyRate cr) {
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
}
