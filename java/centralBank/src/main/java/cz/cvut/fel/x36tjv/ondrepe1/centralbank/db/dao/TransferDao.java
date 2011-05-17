package cz.cvut.fel.x36tjv.ondrepe1.centralbank.db.dao;

import cz.cvut.fel.x36tjv.ondrepe1.centralbank.db.po.TransferPO;
import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferRequest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ondrepe
 */
public class TransferDao {

  @PersistenceContext
  private EntityManager em;

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public boolean setTransfer(TransferRequest request) {
    boolean result = true;
    try {
      TransferPO transfer = new TransferPO();
      transfer.setBankFrom(request.getBankFrom());
      transfer.setAccountFrom(request.getAccountFrom());
      transfer.setBankTo(request.getBankTo());
      transfer.setAccountTo(request.getAccountTo());
      transfer.setCurrency(request.getCurrency());
      transfer.setAmount(request.getAmount());
      transfer.setDescription(request.getDescription());
      transfer.setTransferTime(request.getTime().toGregorianCalendar().getTime());
      em.persist(transfer);
    } catch (Exception ex) {
      result = false;
    }
    return result;
  }
}
