package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CurrencySetCommand extends SetCommand<CurrencyCode> {

  public CurrencySetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void validate(CurrencyCode cc) {

    cc.setCode(cc.getCode().trim().toUpperCase());
    cc.setName(cc.getName().trim());
    if (cc.getDecimalDigits() < 0 || cc.getDecimalDigits() > 3) {
      throw new IBException("Bad decimal digits value!", IBExceptionCode.VALIDATION_FAILED);
    }
  }

  @Override
  protected void set(CurrencyCode cc) {
    CurrencyPO currency;
    currency = em.find(CurrencyPO.class, cc.getCode());
    if (currency == null) {
      Query query = em.createNamedQuery("CurrencyPO.findByCodeAndName").setParameter("name", cc.getName()).setParameter("code", cc.getCode());
      try {
        currency = (CurrencyPO) query.getSingleResult();
        em.remove(currency);
      } catch (PersistenceException ex) {
        try {
          query = em.createNamedQuery("CurrencyPO.findByName").setParameter("name", cc.getName());
          currency = (CurrencyPO) query.getSingleResult();
          em.remove(currency);
        } catch (PersistenceException exc) {
        }
      }
      currency = new CurrencyPO(cc.getCode());
    }
    if (currency != null) {
      currency.setName(cc.getName());
      currency.setDecimalDigits(cc.getDecimalDigits());
    }
    em.persist(currency);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
}
