package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "customer")
@NamedQueries({
  @NamedQuery(name = "CustomerPO.findAll", query = "SELECT c FROM CustomerPO c"),
  @NamedQuery(name = "CustomerPO.findById", query = "SELECT c FROM CustomerPO c WHERE c.id = :id"),
  @NamedQuery(name = "CustomerPO.findByFirstName", query = "SELECT c FROM CustomerPO c WHERE c.firstName = :firstName"),
  @NamedQuery(name = "CustomerPO.findByCustomercol", query = "SELECT c FROM CustomerPO c WHERE c.customercol = :customercol"),
  @NamedQuery(name = "CustomerPO.findByEmail", query = "SELECT c FROM CustomerPO c WHERE c.email = :email")})
public class CustomerPO implements Serializable {
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "lastName")
  private String lastName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 1)
  @Column(name = "valid")
  private String valid;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomer")
  private List<CustomerAccountPO> customerAccountPOList;
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "firstName")
  private String firstName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "Customercol")
  private String customercol;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 500)
  @Column(name = "email")
  private String email;

  public CustomerPO() {
  }

  public CustomerPO(Integer id) {
    this.id = id;
  }

  public CustomerPO(Integer id, String firstName, String customercol, String email) {
    this.id = id;
    this.firstName = firstName;
    this.customercol = customercol;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getCustomercol() {
    return customercol;
  }

  public void setCustomercol(String customercol) {
    this.customercol = customercol;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    if (!(object instanceof CustomerPO)) {
      return false;
    }
    CustomerPO other = (CustomerPO) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO[ id=" + id + " ]";
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getValid() {
    return valid;
  }

  public void setValid(String valid) {
    this.valid = valid;
  }

  public List<CustomerAccountPO> getCustomerAccountPOList() {
    return customerAccountPOList;
  }

  public void setCustomerAccountPOList(List<CustomerAccountPO> customerAccountPOList) {
    this.customerAccountPOList = customerAccountPOList;
  }
  
}
