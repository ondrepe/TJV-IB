package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.statement;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction.TransactionListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.CustomerTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Statement;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.util.ArrayList;
import javax.ejb.SessionContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class StatementSendCommand extends CommonCommand {

  private Queue statementQueue;
  private ConnectionFactory statementQueueFactory;
  
  public StatementSendCommand(EntityManager em, SessionContext ctx, ConnectionFactory factory, Queue queue) {
    super(em, ctx);
    statementQueue = queue;
    statementQueueFactory = factory;
  }
  
  public void sendTransactionsToQueue(int idAccount) {
    
    TransactionListByIdCommand trCommand = new TransactionListByIdCommand(em, ctx);
    ArrayList<Transaction> transactions = (ArrayList<Transaction>) trCommand.execute(idAccount);
    CustomerTranslator translator = new CustomerTranslator();
    Customer customer = translator.translate(getCustomer());
    Account account = getAccount(idAccount);
    
    try {
      Statement st = new Statement();
      st.setCustomer(customer);
      st.setAccount(account);
      st.setTransactions(transactions);
      sendMessageToQueue(st);
    } catch (JMSException ex) {
      System.err.println(ex);
    }
  }
  
  private void sendMessageToQueue(Statement msg) throws JMSException {
    Connection connection = statementQueueFactory.createConnection();
    try {
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      try {
        MessageProducer producer = session.createProducer(statementQueue);
        try {
          Message tm = session.createObjectMessage(msg);
          producer.send(tm);
        } finally {
          producer.close();
        }
      } finally {
        session.close();
      }
    } finally {
      connection.close();
    }
  }
  
  private Account getAccount(int idAcc) {
    AccountPO accPo = em.find(AccountPO.class, idAcc);
    Account acc = new Account();
    acc.setAccountNumber(accPo.getAccountNumber());
    acc.setBalance(accPo.getBalance());
    acc.setCurrencyCode(accPo.getCurrency().getCode());
    acc.setId(idAcc);
    return acc;
  }

  @Override
  protected boolean authorize() {
    return true;
  }
  
}
