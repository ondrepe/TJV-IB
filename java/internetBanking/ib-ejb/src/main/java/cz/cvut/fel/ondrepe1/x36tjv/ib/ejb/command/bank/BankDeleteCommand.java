package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GlobalParamCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class BankDeleteCommand extends DeleteCommand {

  public BankDeleteCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void delete(int id) {
    GlobalParamCommand gp = new GlobalParamCommand(em);
    int myBankCode = gp.getNumberParam("MYBANKCODE");
    
    if(id == myBankCode) {
      throw new IBException("Cannot delete your bank!", IBExceptionCode.VALIDATION_FAILED);
    }
    BankPO bank = em.find(BankPO.class, id);
    em.remove(bank);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
  
}
