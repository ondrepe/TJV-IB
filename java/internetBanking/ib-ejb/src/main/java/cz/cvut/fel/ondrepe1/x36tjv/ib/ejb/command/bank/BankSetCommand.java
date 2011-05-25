package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class BankSetCommand extends SetCommand<BankCode> {

  public BankSetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void validate(BankCode bc) {

    bc.setName(bc.getName().trim());
  }

  @Override
  protected void set(BankCode bc) {
    BankPO bank;
    bank = em.find(BankPO.class, bc.getCode());
    if (bank == null) {
      Query query = em.createNamedQuery("BankPO.findByName").setParameter("name", bc.getName());
      try {
        bank = (BankPO) query.getSingleResult();
        em.remove(bank);
        bank = new BankPO();
        bank.setCode(bc.getCode());
      } catch (PersistenceException ex) {
        bank = new BankPO(bc.getCode(), bc.getName());
      }
    }
    if (bank != null) {
      bank.setName(bc.getName());
    }
    em.persist(bank);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
}
