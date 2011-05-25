package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

import java.math.BigDecimal;

/**
 *
 * @author ondrepe
 */
public class CurrencyRate extends CommonTO {
  
  private String code;
  private BigDecimal rate;

  public CurrencyRate() {
  }

  public CurrencyRate(String code, BigDecimal rate) {
    this.code = code;
    this.rate = rate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }
}
