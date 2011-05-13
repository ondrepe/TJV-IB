package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "account")
@NamedQueries({
  @NamedQuery(name = "AccountPO.findAll", query = "SELECT a FROM AccountPO a"),
  @NamedQuery(name = "AccountPO.findById", query = "SELECT a FROM AccountPO a WHERE a.id = :id"),
  @NamedQuery(name = "AccountPO.findByAccountNumber", query = "SELECT a FROM AccountPO a WHERE a.accountNumber = :accountNumber"),
  @NamedQuery(name = "AccountPO.findByBalance", query = "SELECT a FROM AccountPO a WHERE a.balance = :balance"),
  @NamedQuery(name = "AccountPO.findByValid", query = "SELECT a FROM AccountPO a WHERE a.valid = :valid")})
public class AccountPO implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "accountNumber")
  private String accountNumber;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "balance")
  private BigDecimal balance;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 1)
  @Column(name = "valid")
  private String valid;
  @JoinColumn(name = "code", referencedColumnName = "code")
  @ManyToOne(optional = false)
  private CurrencyPO code;
  @OneToMany(mappedBy = "idAccountTo")
  private List<BankTransactionPO> bankTransactionPOList;
  @OneToMany(mappedBy = "idAccountFrom")
  private List<BankTransactionPO> bankTransactionPOList1;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
  private List<CustomerAccountPO> customerAccountPOList;

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

  public CurrencyPO getCode() {
    return code;
  }

  public void setCode(CurrencyPO code) {
    this.code = code;
  }

  public List<BankTransactionPO> getBankTransactionPOList() {
    return bankTransactionPOList;
  }

  public void setBankTransactionPOList(List<BankTransactionPO> bankTransactionPOList) {
    this.bankTransactionPOList = bankTransactionPOList;
  }

  public List<BankTransactionPO> getBankTransactionPOList1() {
    return bankTransactionPOList1;
  }

  public void setBankTransactionPOList1(List<BankTransactionPO> bankTransactionPOList1) {
    this.bankTransactionPOList1 = bankTransactionPOList1;
  }

  public List<CustomerAccountPO> getCustomerAccountPOList() {
    return customerAccountPOList;
  }

  public void setCustomerAccountPOList(List<CustomerAccountPO> customerAccountPOList) {
    this.customerAccountPOList = customerAccountPOList;
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
    if (!(object instanceof AccountPO)) {
      return false;
    }
    AccountPO other = (AccountPO) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO[ id=" + id + " ]";
  }
  
}
