package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

/**
 *
 * @author ondrepe
 */
public class CurrencyCode extends CommonTO {

  private String code;
  private String name;
  private Integer decimalDigits;

  public CurrencyCode() {
  }

  public CurrencyCode(String code, String name, Integer decimalDigits) {
    this.code = code;
    this.name = name;
    this.decimalDigits = decimalDigits;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getDecimalDigits() {
    return decimalDigits;
  }

  public void setDecimalDigits(Integer decimalDigits) {
    this.decimalDigits = decimalDigits;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
