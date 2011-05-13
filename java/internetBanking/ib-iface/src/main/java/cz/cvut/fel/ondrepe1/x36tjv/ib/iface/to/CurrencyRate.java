package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

/**
 *
 * @author ondrepe
 */
public class CurrencyRate extends AbstractIFaceObject {
  
  private String code;
  private Double rate;

  public CurrencyRate() {
  }

  public CurrencyRate(String code, Double rate) {
    this.code = code;
    this.rate = rate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }
}
