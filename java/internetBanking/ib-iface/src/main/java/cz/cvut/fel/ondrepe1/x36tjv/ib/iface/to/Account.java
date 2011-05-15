package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

import java.math.BigDecimal;

/**
 *
 * @author ondrepe
 */
public class Account extends AbstractIFaceObject {
  
  private Integer id;
  private Integer customerId;
  private String currencyCode;
  private BigDecimal balance;

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
