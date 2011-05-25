package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author ondrepe
 */
@Entity
@Cache(alwaysRefresh=true)
@Table(name = "autentization")
public class AutentizationPO extends CommonPO {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "login")
  private String login;
  
  @Column(name = "password")
  private String password;
  
  @JoinColumn(name = "groupName", referencedColumnName = "groupName")
  @ManyToOne
  private AutentizationGroupPO group;
  
  @JoinColumn(name = "idCustomer", referencedColumnName = "id")
  @ManyToOne
  private CustomerPO customer;

  public AutentizationPO() {
  }

  public AutentizationPO(String login) {
    this.login = login;
  }

  public AutentizationPO(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AutentizationGroupPO getGroup() {
    return group;
  }

  public void setGroup(AutentizationGroupPO group) {
    this.group = group;
  }

  public CustomerPO getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerPO customer) {
    this.customer = customer;
  }
  
}
