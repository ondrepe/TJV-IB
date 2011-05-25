package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CommonPO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CreateUpdateCommand<PO extends CommonPO> extends CommonCommand {

  public CreateUpdateCommand(EntityManager em) {
    super(em);
  }

  public CreateUpdateCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public abstract void execute(PO object);
  
  @Override
  protected boolean authorize() {
    return true;
  }
}
