package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CommonCommand {
  
  protected EntityManager em;

  public CommonCommand(EntityManager em) {
    this.em = em;
  }
}
