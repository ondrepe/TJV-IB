package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Statement;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ondrepe
 */
@MessageDriven(mappedName = "jms/statementQueue", activationConfig = {
  @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
  @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MailBean implements MessageListener {

  @Resource(name = "mail/ibMailSession")
  private Session mailSession;

  @Override
  public void onMessage(javax.jms.Message msg) {
    try {
      ObjectMessage tm = (ObjectMessage) msg;
      Statement statement = (Statement) tm.getObject();
      System.err.println(statement.getCustomer().getFirstName() + " " + statement.getCustomer().getLastName());
      System.err.println(statement.getCustomer().getEmail());
      System.err.println("Transactions: " + statement.getTransactions().size());
      sendEmail(statement);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  private void sendEmail(Statement statement) {
    try {
      Message mail = new MimeMessage(mailSession);
      mail.setFrom();
      mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(statement.getCustomer().getEmail(), false));
      mail.setHeader("X-Mailer", "My Mailer");
      mail.setSentDate(new Date());
      mail.setSubject("[OndrepeBank] - Bank Statement - " + statement.getAccount().getAccountNumber());
      //mail.setText();
      mail.setContent(createMessageText(statement), "text/html");
      Transport.send(mail);

    } catch (MessagingException mex) {
      System.err.println(mex);
    }
  }
  
  private String createMessageText(Statement statement) {
    StringBuilder builder = new StringBuilder();
    builder.append("<html lang=\"en\"><head>").append(LineSeparator.Web);
    builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />").append(LineSeparator.Web);
    builder.append("<title>Bank Statement</title><s/head><body>").append(LineSeparator.Web);
    builder.append("<p>")
            .append("Customer: ")
            .append(statement.getCustomer()
            .getFirstName()).append(" ")
            .append(statement.getCustomer().getLastName())
            .append("</p>")
            .append(LineSeparator.Web);
    builder.append("<p>")
            .append("Account number: ")
            .append(statement.getAccount().getAccountNumber())
            .append(" Actual balance: ")
            .append(statement.getAccount().getBalance())
            .append(" ")
            .append(statement.getAccount().getCurrencyCode())
            .append("</p>")
            .append(LineSeparator.Web);
    builder.append("<br />").append(LineSeparator.Web);
    builder.append("<p>Transactions: </p>").append(LineSeparator.Web);
    builder.append("<table>");
    builder.append("<thead><tr>")
            .append("<th>Id</th>")         
            .append("<th>Account from</th>")
            .append("<th>Amount from</th>")
            .append("<th>Account to</th>")
            .append("<th>Amount to</th>")
            .append("<th>Description</th>")
            .append("<th>Transaction date &amp; time</th>") 
            .append("</tr></thead>")
            .append(LineSeparator.Web);
    builder.append("<tbody>").append(LineSeparator.Web);
    
    for(Transaction trn : statement.getTransactions()) {
      builder.append("<tr>");
      builder.append("<td>").append(trn.getId()).append("</td>");
      builder.append("<td>").append(trn.getAccountFrom()).append("/").append(trn.getBankFrom()).append("</td>");
      builder.append("<td>").append(trn.getAmmountFrom()).append(" ").append(trn.getCurrencyCodeFrom()).append("</td>");
      builder.append("<td>").append(trn.getAccountTo()).append("/").append(trn.getBankTo()).append("</td>");
      builder.append("<td>").append(trn.getAmmountTo()).append(" ").append(trn.getCurrencyCodeTo()).append("</td>");
      builder.append("<td>").append(trn.getDescription()).append("</td>");
      builder.append("<td>").append(trn.getTransactionTime()).append("</td>");
      builder.append("</tr>").append(LineSeparator.Web);
    }
    
    builder.append("</tbody></table>").append(LineSeparator.Web);
    builder.append("</body>").append("</html>");
    return builder.toString();
  }
}
