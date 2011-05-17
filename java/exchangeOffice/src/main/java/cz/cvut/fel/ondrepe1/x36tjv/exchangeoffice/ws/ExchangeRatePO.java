package cz.cvut.fel.ondrepe1.x36tjv.exchangeoffice.ws;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "exchangerate")
public class ExchangeRatePO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "currency")
  private String currency;
  
  @Column(name = "rate")
  private BigDecimal rate;

  public ExchangeRatePO() {
  }

  public ExchangeRatePO(String currency) {
    this.currency = currency;
  }

  public ExchangeRatePO(String currency, BigDecimal rate) {
    this.currency = currency;
    this.rate = rate;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }
}
