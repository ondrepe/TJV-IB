package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.GlobalParamPO;
import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferRequest;
import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferResponse;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 *
 * @author ondrepe
 */
public class CentralBankClient extends CommonCommand {
  
  private WebServiceTemplate webServiceTemplate;
  private Jaxb2Marshaller marshaller;
  
  public CentralBankClient(EntityManager em) {
    super(em);
    marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("cz.cvut.fel.x36tjv.ondrepe1.centralbankws");
    webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(marshaller);
    webServiceTemplate.setUnmarshaller(marshaller);
  }

  public void transfer(BankTransactionPO btPo) {
    TransferRequest request = new TransferRequest();
    request.setAccountFrom(btPo.getAccountNumberFrom());
    request.setAccountTo(btPo.getAccountNumberTo());
    request.setAmount(btPo.getAmountTo());
    request.setBankFrom(btPo.getBankFrom().getCode());
    request.setBankTo(btPo.getBankTo().getCode());
    request.setCurrency(btPo.getCurrencyTo().getCode());
    request.setDescription(btPo.getDescription());
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(btPo.getCreationTime());
    XMLGregorianCalendar xmlCal = null;
    try {
      xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    } catch (DatatypeConfigurationException ex) {
      System.err.println("Could not create XMLGregorianCalendar.");
    }
    request.setTime(xmlCal);
    
    TransferResponse response = (TransferResponse) webServiceTemplate.marshalSendAndReceive(getEndpoint(), request);
  }
  
  private String getEndpoint() {
    GlobalParamPO gpPo = em.find(GlobalParamPO.class, "CB_ENDPOINT");
    return gpPo.getValue();
  }
}
