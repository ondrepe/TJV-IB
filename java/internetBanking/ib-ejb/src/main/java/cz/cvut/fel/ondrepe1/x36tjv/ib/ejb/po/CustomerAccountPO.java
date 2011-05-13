package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "customeraccount")
@NamedQueries({
  @NamedQuery(name = "CustomerAccountPO.findAll", query = "SELECT c FROM CustomerAccountPO c"),
  @NamedQuery(name = "CustomerAccountPO.findById", query = "SELECT c FROM CustomerAccountPO c WHERE c.id = :id")})
public class CustomerAccountPO implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @JoinColumn(name = "idCustomer", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private CustomerPO idCustomer;
  @JoinColumn(name = "idAccount", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private AccountPO idAccount;

  public CustomerAccountPO() {
  }

  public CustomerAccountPO(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CustomerPO getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(CustomerPO idCustomer) {
    this.idCustomer = idCustomer;
  }

  public AccountPO getIdAccount() {
    return idAccount;
  }

  public void setIdAccount(AccountPO idAccount) {
    this.idAccount = idAccount;
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
    if (!(object instanceof CustomerAccountPO)) {
      return false;
    }
    CustomerAccountPO other = (CustomerAccountPO) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerAccountPO[ id=" + id + " ]";
  }
  
}
