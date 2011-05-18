package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.statement.StatementSendCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IStatementBean;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ondrepe
 */
@Stateless
@DeclareRoles({"CUSTOMER", "MANAGER"})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StatementBean implements IStatementBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;
  
  @Resource(name = "jms/statementQueue")
  private Queue statementQueue;
  @Resource(name = "jms/statementQueueFactory")
  private ConnectionFactory statementQueueFactory;

  @Override
  @RolesAllowed({"CUSTOMER"})
  public void sendStatement(Integer intgr) {
    StatementSendCommand command = new StatementSendCommand(em, ctx, statementQueueFactory, statementQueue);
    command.sendTransactionsToQueue(intgr);
  }
}
