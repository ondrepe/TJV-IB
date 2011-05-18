package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "account")
@NamedQueries({
  @NamedQuery(name = "AccountPO.findByAccNum", query = "SELECT a FROM AccountPO a WHERE a.accountNumber = :accountNumber AND a.valid = :valid"),
  @NamedQuery(name = "AccountPO.getAccNums", query = "SELECT a.accountNumber FROM AccountPO a ORDER BY a.accountNumber DESC"),
  @NamedQuery(name = "AccountPO.findByValid", query = "SELECT a FROM AccountPO a WHERE a.valid = :valid")})
public class AccountPO implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "idAccTableGen", strategy=GenerationType.TABLE)
  @TableGenerator(name = "idAccTableGen", table = "idtable", pkColumnName = "name", valueColumnName = "val", pkColumnValue = "account", initialValue = 10000, allocationSize = 200)
  private Integer id;
  
  @Column(name = "accountNumber")
  private String accountNumber;

  @Column(name = "balance")
  private BigDecimal balance;
  
  @Column(name = "valid")
  private String valid;
  
  @JoinColumn(name = "code", referencedColumnName = "code")
  @ManyToOne(optional = false)
  private CurrencyPO currency;
  
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "customeraccount", joinColumns = { @JoinColumn(name = "idAccount")}, inverseJoinColumns={@JoinColumn(name="idCustomer")})
  private List<CustomerPO> customers;

  public AccountPO() {
  }

  public AccountPO(Integer id) {
    this.id = id;
  }

  public AccountPO(Integer id, String accountNumber, BigDecimal balance, String valid) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.valid = valid;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getValid() {
    return valid;
  }

  public void setValid(String valid) {
    this.valid = valid;
  }

  public CurrencyPO getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyPO currency) {
    this.currency = currency;
  }

  public List<CustomerPO> getCustomers() {
    if(customers == null) {
      customers = new ArrayList<CustomerPO>();
    }
    return customers;
  }
}
