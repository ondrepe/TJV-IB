package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "autentization")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "AutentizationPO.findAll", query = "SELECT a FROM AutentizationPO a"),
  @NamedQuery(name = "AutentizationPO.findById", query = "SELECT a FROM AutentizationPO a WHERE a.id = :id"),
  @NamedQuery(name = "AutentizationPO.findByLogin", query = "SELECT a FROM AutentizationPO a WHERE a.login = :login"),
  @NamedQuery(name = "AutentizationPO.getCustomerByLogin", query = "SELECT a.customer FROM AutentizationPO a WHERE a.login = :login"),
  @NamedQuery(name = "AutentizationPO.findByPassword", query = "SELECT a FROM AutentizationPO a WHERE a.password = :password")})
public class AutentizationPO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  
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

  public AutentizationPO(Integer id) {
    this.id = id;
  }

  public AutentizationPO(Integer id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
