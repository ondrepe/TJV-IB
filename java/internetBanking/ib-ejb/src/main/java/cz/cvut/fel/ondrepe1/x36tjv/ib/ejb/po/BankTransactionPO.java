package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "banktransaction")
@NamedQueries({
  @NamedQuery(name = "BankTransactionPO.findAll", query = "SELECT b FROM BankTransactionPO b"),
  @NamedQuery(name = "BankTransactionPO.findById", query = "SELECT b FROM BankTransactionPO b WHERE b.id = :id"),
  @NamedQuery(name = "BankTransactionPO.findByAccountFrom", query = "SELECT b FROM BankTransactionPO b WHERE b.accountFrom = :accountFrom"),
  @NamedQuery(name = "BankTransactionPO.findByAccountTo", query = "SELECT b FROM BankTransactionPO b WHERE b.accountTo = :accountTo"),
  @NamedQuery(name = "BankTransactionPO.findByAmountFrom", query = "SELECT b FROM BankTransactionPO b WHERE b.amountFrom = :amountFrom"),
  @NamedQuery(name = "BankTransactionPO.findByAmountTo", query = "SELECT b FROM BankTransactionPO b WHERE b.amountTo = :amountTo"),
  @NamedQuery(name = "BankTransactionPO.findByDescription", query = "SELECT b FROM BankTransactionPO b WHERE b.description = :description"),
  @NamedQuery(name = "BankTransactionPO.findByCreationTime", query = "SELECT b FROM BankTransactionPO b WHERE b.creationTime = :creationTime")})
public class BankTransactionPO implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "accountFrom")
  private String accountFrom;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "accountTo")
  private String accountTo;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "amountFrom")
  private BigDecimal amountFrom;
  @Basic(optional = false)
  @NotNull
  @Column(name = "amountTo")
  private BigDecimal amountTo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "description")
  private String description;
  @Basic(optional = false)
  @NotNull
  @Column(name = "creationTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationTime;
  @JoinColumn(name = "idAccountTo", referencedColumnName = "id")
  @ManyToOne
  private AccountPO idAccountTo;
  @JoinColumn(name = "idAccountFrom", referencedColumnName = "id")
  @ManyToOne
  private AccountPO idAccountFrom;
  @JoinColumn(name = "currencyTo", referencedColumnName = "code")
  @ManyToOne(optional = false)
  private CurrencyPO currencyTo;
  @JoinColumn(name = "currencyFrom", referencedColumnName = "code")
  @ManyToOne(optional = false)
  private CurrencyPO currencyFrom;
  @JoinColumn(name = "bankTo", referencedColumnName = "code")
  @ManyToOne(optional = false)
  private BankPO bankTo;
  @JoinColumn(name = "bankFrom", referencedColumnName = "code")
  @ManyToOne(optional = false)
  private BankPO bankFrom;

  public BankTransactionPO() {
  }

  public BankTransactionPO(Integer id) {
    this.id = id;
  }

  public BankTransactionPO(Integer id, String accountFrom, String accountTo, BigDecimal amountFrom, BigDecimal amountTo, String description, Date creationTime) {
    this.id = id;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.amountFrom = amountFrom;
    this.amountTo = amountTo;
    this.description = description;
    this.creationTime = creationTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public BigDecimal getAmountFrom() {
    return amountFrom;
  }

  public void setAmountFrom(BigDecimal amountFrom) {
    this.amountFrom = amountFrom;
  }

  public BigDecimal getAmountTo() {
    return amountTo;
  }

  public void setAmountTo(BigDecimal amountTo) {
    this.amountTo = amountTo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public AccountPO getIdAccountTo() {
    return idAccountTo;
  }

  public void setIdAccountTo(AccountPO idAccountTo) {
    this.idAccountTo = idAccountTo;
  }

  public AccountPO getIdAccountFrom() {
    return idAccountFrom;
  }

  public void setIdAccountFrom(AccountPO idAccountFrom) {
    this.idAccountFrom = idAccountFrom;
  }

  public CurrencyPO getCurrencyTo() {
    return currencyTo;
  }

  public void setCurrencyTo(CurrencyPO currencyTo) {
    this.currencyTo = currencyTo;
  }

  public CurrencyPO getCurrencyFrom() {
    return currencyFrom;
  }

  public void setCurrencyFrom(CurrencyPO currencyFrom) {
    this.currencyFrom = currencyFrom;
  }

  public BankPO getBankTo() {
    return bankTo;
  }

  public void setBankTo(BankPO bankTo) {
    this.bankTo = bankTo;
  }

  public BankPO getBankFrom() {
    return bankFrom;
  }

  public void setBankFrom(BankPO bankFrom) {
    this.bankFrom = bankFrom;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof BankTransactionPO)) {
      return false;
    }
    BankTransactionPO other = (BankTransactionPO) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO[ id=" + id + " ]";
  }
  
}
