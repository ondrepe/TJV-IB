package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.EntityExistIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class BankSetCommand extends CommonSetCommand<BankCode> {

  public BankSetCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected void validate(BankCode bc) throws CommonIBException {

    bc.setName(bc.getName().trim());

    // validace jestli nahodou nexistuje
    Query query = em.createNamedQuery("BankPO.findByCodeAndName").setParameter("name", bc.getName()).setParameter("code", bc.getCode());
    try {
      BankPO bank = (BankPO) query.getSingleResult();
      if (bank != null) {
        throw new EntityExistIBException("BankCode");
      }
    } catch (PersistenceException ex) {
    }
  }

  @Override
  protected void execute(BankCode bc) {
    BankPO bank;
    bank = em.find(BankPO.class, bc.getCode());
    if (bank == null) {
      Query query = em.createNamedQuery("BankPO.findByName").setParameter("name", bc.getName());
      try {
        bank = (BankPO) query.getSingleResult();
        bank.setCode(bc.getCode());
      } catch (PersistenceException ex) {
        bank = new BankPO(bc.getCode(), bc.getName());
      }
    }
    if (bank == null) {
      bank.setName(bc.getName());
    }
    em.persist(bank);
  }
}
