package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.ExchangeRate;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.bean.ManagedProperty;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ondrepe
 */
public class ExchangeOfficeClient {

  private static final String shareURL = "http://localhost:8080/ExchangeOffice/exchangerate/";
  @ManagedProperty("#{param['currencyFromTo']}")
  private String currencyFromTo;

  public BigDecimal getRate(CurrencyPO from, CurrencyPO to) {
    if (!from.getCode().equals(to.getCode())) {
      try {
        currencyFromTo = from.getCode() + to.getCode();
        URL url = new URL(shareURL + currencyFromTo);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        JAXBContext ctx = JAXBContext.newInstance(ExchangeRate.class);
        Unmarshaller um = ctx.createUnmarshaller();
        ExchangeRate s = (ExchangeRate) um.unmarshal(conn.getInputStream());
        return new BigDecimal(s.getExchangeRate());
      } catch (Exception ex) {
        System.err.println(ex);
      }
    }
    return BigDecimal.ONE;
  }
}
