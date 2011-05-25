package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.payment.PayInterestCommand;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
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
@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PayInterestBean {
  
  @PersistenceContext
  private EntityManager em;
  
  @Schedule
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void payInterest() {
    System.err.println("PayInterest");
    PayInterestCommand command = new PayInterestCommand(em);
    command.payInterest();
  }
}
