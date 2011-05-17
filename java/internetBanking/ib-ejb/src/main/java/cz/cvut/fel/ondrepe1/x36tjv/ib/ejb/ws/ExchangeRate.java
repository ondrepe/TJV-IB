package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name = "exchangerate")
public class ExchangeRate {

  private String currencyFromTo;
  private String exchangeRate;
  
  public ExchangeRate() {}
  
  public ExchangeRate(String currencyFromTo, String exchangeRate) {
    this.currencyFromTo = currencyFromTo;
    this.exchangeRate = exchangeRate;
  }

  @XmlElement
  public String getCurrencyFromTo() {
    return currencyFromTo;
  }

  public void setCurrencyFromTo(String currencyFromTo) {
    this.currencyFromTo = currencyFromTo;
  }

  @XmlElement
  public String getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(String exchangeRate) {
    this.exchangeRate = exchangeRate;
  }
}
