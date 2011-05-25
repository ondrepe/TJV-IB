package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.GlobalParamPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.ExchangeRate;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ondrepe
 */
public class ExchangeOfficeClient extends CommonCommand {

  public ExchangeOfficeClient(EntityManager em) {
    super(em);
  }

  @ManagedProperty("#{param['currencyFromTo']}")
  private String currencyFromTo;

  public BigDecimal getRate(CurrencyPO from, CurrencyPO to) {
    if (!from.getCode().equals(to.getCode())) {
      try {
        currencyFromTo = from.getCode() + to.getCode();
        URL url = new URL(getEndpoint() + currencyFromTo);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        JAXBContext jaxbCtx = JAXBContext.newInstance(ExchangeRate.class);
        Unmarshaller um = jaxbCtx.createUnmarshaller();
        ExchangeRate s = (ExchangeRate) um.unmarshal(conn.getInputStream());
        return new BigDecimal(s.getExchangeRate());
      } catch (Exception ex) {
        throw new IBException("Exchange Office error.", IBExceptionCode.WS_FAILED, ex);
      }
    }
    return BigDecimal.ONE;
  }
  
  private String getEndpoint() {
    GlobalParamPO gpPo = em.find(GlobalParamPO.class, "EO_ENDPOINT");
    return gpPo.getValue();
  }

  @Override
  protected boolean authorize() {
    return true;
  }
}
