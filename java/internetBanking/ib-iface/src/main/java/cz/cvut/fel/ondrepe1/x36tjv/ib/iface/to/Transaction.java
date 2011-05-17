package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ondrepe
 */
public class Transaction extends AbstractIFaceObject {
  
  private Integer id;
  private String accountFrom;
  private String accountTo;
  private Integer bankFrom;
  private Integer bankTo;
  private BigDecimal ammountTo;
  private String currencyCode;
  private String description;
  private Date transactionTime;

  public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public String getAccountTo() {
    return accountTo;
  }

  public Integer getBankFrom() {
    return bankFrom;
  }

  public void setBankFrom(Integer bankFrom) {
    this.bankFrom = bankFrom;
  }

  public Integer getBankTo() {
    return bankTo;
  }

  public void setBankTo(Integer bankTo) {
    this.bankTo = bankTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public BigDecimal getAmmountTo() {
    return ammountTo;
  }

  public void setAmmountTo(BigDecimal ammountTo) {
    this.ammountTo = ammountTo;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(Date transactionTime) {
    this.transactionTime = transactionTime;
  }
}
