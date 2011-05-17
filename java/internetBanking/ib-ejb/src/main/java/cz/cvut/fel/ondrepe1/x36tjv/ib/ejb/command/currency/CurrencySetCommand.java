package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.EntityExistIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CurrencySetCommand extends CommonSetCommand<CurrencyCode> {

  public CurrencySetCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected void validate(CurrencyCode cc) throws CommonIBException {

    cc.setCode(cc.getCode().trim().toUpperCase());
    cc.setName(cc.getName().trim());

    // validace jestli nahodou nexistuje
    Query query = em.createNamedQuery("CurrencyPO.findByCodeAndNameAndDD").setParameter("name", cc.getName()).setParameter("code", cc.getCode()).setParameter("decimalDigits", cc.getDecimalDigits());
    try {
      CurrencyPO bank = (CurrencyPO) query.getSingleResult();
      if (bank != null) {
        throw new EntityExistIBException("CurrencyCode");
      }
    } catch (PersistenceException ex) {}
  }

  @Override
  protected void execute(CurrencyCode cc) {
    CurrencyPO currency;
    currency = em.find(CurrencyPO.class, cc.getCode());
    if (currency == null) {
      Query query = em.createNamedQuery("CurrencyPO.findByName").setParameter("name", cc.getName());
      try {
        currency = (CurrencyPO) query.getSingleResult();
        currency.setCode(cc.getCode());
      } catch (PersistenceException ex) {
        currency = new CurrencyPO(cc.getCode(), cc.getName(), cc.getDecimalDigits());
      }
    }
    if (currency == null) {
      currency.setName(cc.getName());
    }
    em.persist(currency);
  }
}
