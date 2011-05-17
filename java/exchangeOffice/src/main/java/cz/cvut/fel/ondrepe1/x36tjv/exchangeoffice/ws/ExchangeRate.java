package cz.cvut.fel.ondrepe1.x36tjv.exchangeoffice.ws;

import java.math.BigDecimal;
import java.net.URI;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name = "exchangerate")
public class ExchangeRate {

  private String currencyFromTo;
  private BigDecimal exchangeRate;
  private URI uri;
  
  public ExchangeRate() {}
  
  public ExchangeRate(String currencyFromTo, BigDecimal exchangeRate, URI uri) {
    this.currencyFromTo = currencyFromTo;
    this.exchangeRate = exchangeRate;
    this.uri = uri;
  }

  @XmlElement
  public String getCurrencyFromTo() {
    return currencyFromTo;
  }

  public void setCurrencyFromTo(String currencyFromTo) {
    this.currencyFromTo = currencyFromTo;
  }

  @XmlElement
  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  @XmlAttribute
  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }
}
