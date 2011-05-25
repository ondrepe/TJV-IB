package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.BankTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class BankListCommand extends ListCommand<BankPO, BankCode> {

  public BankListCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<BankPO> list() {
    Query query = em.createNamedQuery("BankPO.findAll");
    return query.getResultList();
  }

  @Override
  protected List<BankCode> convert(List<BankPO> list) {
    BankTranslator translator = new BankTranslator();
    return translator.translateList(list);
  }

  @Override
  protected boolean authorize() {
    return isManager() || isCustomer();
  }
  
}
