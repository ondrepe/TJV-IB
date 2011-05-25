package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author ondrepe
 */
@Entity
@Cache(alwaysRefresh=true)
@Table(name = "banktransaction")
@NamedQueries({
  @NamedQuery(name = "BankTransactionPO.findAll", query = "SELECT b FROM BankTransactionPO b"),
  @NamedQuery(name = "BankTransactionPO.findById", query = "SELECT b FROM BankTransactionPO b WHERE b.id = :id"),
  @NamedQuery(name = "BankTransactionPO.findById2", query = "SELECT b FROM BankTransactionPO b WHERE b.accountTo.id = :id OR b.accountFrom.id = :id ORDER BY b.creationTime DESC"),
  @NamedQuery(name = "BankTransactionPO.findByAccountFrom", query = "SELECT b FROM BankTransactionPO b WHERE b.accountFrom = :accountFrom"),
  @NamedQuery(name = "BankTransactionPO.findByAccountTo", query = "SELECT b FROM BankTransactionPO b WHERE b.accountTo = :accountTo"),
  @NamedQuery(name = "BankTransactionPO.findByAmountFrom", query = "SELECT b FROM BankTransactionPO b WHERE b.amountFrom = :amountFrom"),
  @NamedQuery(name = "BankTransactionPO.findByAmountTo", query = "SELECT b FROM BankTransactionPO b WHERE b.amountTo = :amountTo"),
  @NamedQuery(name = "BankTransactionPO.findByDescription", query = "SELECT b FROM BankTransactionPO b WHERE b.description = :description"),
  @NamedQuery(name = "BankTransactionPO.findByCreationTime", query = "SELECT b FROM BankTransactionPO b WHERE b.creationTime = :creationTime")})
public class BankTransactionPO extends CommonPO {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "idTrTableGen", strategy=GenerationType.TABLE)
  @TableGenerator(name = "idTrTableGen", table = "idtable", pkColumnName = "name", valueColumnName = "val", pkColumnValue = "transaction", initialValue = 10000, allocationSize = 200)
  private Integer id;
  
  @Column(name = "accountFrom")
  private String accountNumberFrom;
  
  @Column(name = "accountTo")
  private String accountNumberTo;
  
  @Column(name = "amountFrom")
  private BigDecimal amountFrom;
  
  @Column(name = "amountTo")
  private BigDecimal amountTo;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "creationTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationTime;
  
  @JoinColumn(name = "idAccountTo", referencedColumnName = "id")
  @ManyToOne
  private AccountPO accountTo;
  
  @JoinColumn(name = "idAccountFrom", referencedColumnName = "id")
  @ManyToOne
  private AccountPO accountFrom;
  
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
    this.accountNumberFrom = accountFrom;
    this.accountNumberTo = accountTo;
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

  public String getAccountNumberFrom() {
    return accountNumberFrom;
  }

  public void setAccountNumberFrom(String accountNumberFrom) {
    this.accountNumberFrom = accountNumberFrom;
  }

  public String getAccountNumberTo() {
    return accountNumberTo;
  }

  public void setAccountNumberTo(String accountNumberTo) {
    this.accountNumberTo = accountNumberTo;
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

  public AccountPO getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(AccountPO accountTo) {
    this.accountTo = accountTo;
  }

  public AccountPO getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(AccountPO accountFrom) {
    this.accountFrom = accountFrom;
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
}
