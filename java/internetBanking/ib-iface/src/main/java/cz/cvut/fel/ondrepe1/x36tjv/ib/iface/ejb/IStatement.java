package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface IStatement {
  
  public void sendStatement(Integer idAccount);
}
