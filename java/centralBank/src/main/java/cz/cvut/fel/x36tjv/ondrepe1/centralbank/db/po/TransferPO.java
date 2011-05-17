package cz.cvut.fel.x36tjv.ondrepe1.centralbank.db.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "transfer")
public class TransferPO implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "idTransfer")
  @GeneratedValue(generator = "idTrnTableGen", strategy=GenerationType.TABLE)
  @TableGenerator(name = "idTrnTableGen", table = "idtable", pkColumnName = "name", valueColumnName = "val", pkColumnValue = "transaction", initialValue = 10000, allocationSize = 200)
  private Integer idTransfer;
  
  @Column(name = "bankFrom")
  private int bankFrom;
  
  @Column(name = "accountFrom")
  private String accountFrom;
  
  @Column(name = "bankTo")
  private int bankTo;

  @Column(name = "accountTo")
  private String accountTo;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "currency")
  private String currency;

  @Column(name = "description")
  private String description;

  @Column(name = "transferTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date transferTime;

  public TransferPO() {
  }

  public TransferPO(Integer id) {
    this.idTransfer = id;
  }

  public TransferPO(Integer id, int bankFrom, String accountFrom, int bankTo, String accountTo, BigDecimal amount, String currency, String description, Date dateTime) {
    this.idTransfer = id;
    this.bankFrom = bankFrom;
    this.accountFrom = accountFrom;
    this.bankTo = bankTo;
    this.accountTo = accountTo;
    this.amount = amount;
    this.currency = currency;
    this.description = description;
    this.transferTime = dateTime;
  }

  public Integer getIdTransfer() {
    return idTransfer;
  }

  public void setIdTransfer(Integer idTransfer) {
    this.idTransfer = idTransfer;
  }

  public int getBankFrom() {
    return bankFrom;
  }

  public void setBankFrom(int bankFrom) {
    this.bankFrom = bankFrom;
  }

  public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public int getBankTo() {
    return bankTo;
  }

  public void setBankTo(int bankTo) {
    this.bankTo = bankTo;
  }

  public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getTransferTime() {
    return transferTime;
  }

  public void setTransferTime(Date transferTime) {
    this.transferTime = transferTime;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idTransfer != null ? idTransfer.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TransferPO)) {
      return false;
    }
    TransferPO other = (TransferPO) object;
    if ((this.idTransfer == null && other.idTransfer != null) || (this.idTransfer != null && !this.idTransfer.equals(other.idTransfer))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.x36tjv.ondrepe1.centralbank.po.Transfer[id=" + idTransfer + "]";
  }
}
