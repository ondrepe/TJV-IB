package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction.TransactionTransferMoneyCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ITransactionBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ondrepe
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TransactionBean implements ITransactionBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public List<Transaction> getList() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Transaction> getListByAccountId(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void transferMoney(Transaction trnsfr) throws CommonIBException {
    CommonSetCommand<Transaction> command = new TransactionTransferMoneyCommand(em);
    command.set(trnsfr);
  }
  
}
