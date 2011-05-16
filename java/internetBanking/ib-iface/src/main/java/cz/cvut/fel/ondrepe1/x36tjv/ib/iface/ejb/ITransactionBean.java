package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ondrepe
 */
@Local
public interface ITransactionBean {
  
  public List<Transaction> getList();
  public List<Transaction> getListByAccountId(int accountId);
}
