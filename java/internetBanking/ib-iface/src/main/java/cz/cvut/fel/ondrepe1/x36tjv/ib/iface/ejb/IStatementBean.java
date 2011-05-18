package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface IStatementBean {
  
  public void sendStatement(Integer idAccount);
}
