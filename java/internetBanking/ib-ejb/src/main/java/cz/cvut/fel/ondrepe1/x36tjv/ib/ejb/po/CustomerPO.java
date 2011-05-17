package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.OptimisticLocking;
import org.eclipse.persistence.annotations.OptimisticLockingType;

/**
 *
 * @author ondrepe
 */
@Entity
@Cache(alwaysRefresh=true)
@Table(name = "customer")
@OptimisticLocking(type= OptimisticLockingType.ALL_COLUMNS)
@NamedQueries({
  @NamedQuery(name = "CustomerPO.findAll", query = "SELECT c FROM CustomerPO c"),
  @NamedQuery(name = "CustomerPO.findByValid", query = "SELECT c FROM CustomerPO c WHERE c.valid = :valid"),
  @NamedQuery(name = "CustomerPO.findByAll", query = "SELECT c FROM CustomerPO c WHERE c.firstName = :firstName AND c.lastName = :lastName AND c.email = :email")})
public class CustomerPO implements Serializable {
    
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "idCustTableGen", strategy=GenerationType.TABLE)
  @TableGenerator(name = "idCustTableGen", table = "idtable", pkColumnName = "name", valueColumnName = "val", pkColumnValue = "customer", initialValue = 10000, allocationSize = 200)
  private Integer id;
  
  @Column(name = "firstName")
  private String firstName;
  
  @Column(name = "lastName")
  private String lastName;
  
  @Column(name = "email")
  private String email;

  @Column(name = "valid")
  private String valid;
  
  @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
  @JoinTable(name = "customeraccount", joinColumns = { @JoinColumn(name = "idCustomer")}, inverseJoinColumns={@JoinColumn(name="idAccount")})
  private List<AccountPO> accounts;
  
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
  private AutentizationPO autentization;
  
  public CustomerPO() {
  }

  public CustomerPO(Integer id) {
    this.id = id;
  }

  public CustomerPO(Integer id, String firstName, String lastName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public List<AccountPO> getAccounts() {
    if(accounts == null) {
      accounts = new ArrayList<AccountPO>();
    }
    return accounts;
  }

  public AutentizationPO getAutentization() {
    return autentization;
  }

  public void setAutentization(AutentizationPO autentization) {
    this.autentization = autentization;
  }
  
}
