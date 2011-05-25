package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author ondrepe
 */
@Entity
@Cache(alwaysRefresh=true)
@Table(name = "bank")
@NamedQueries({
  @NamedQuery(name = "BankPO.findAll", query = "SELECT b FROM BankPO b"),
  @NamedQuery(name = "BankPO.findAllWithout", query = "SELECT b FROM BankPO b WHERE b.code NOT IN(:code)"),
  @NamedQuery(name = "BankPO.findByName", query = "SELECT b FROM BankPO b WHERE b.name = :name"),
  @NamedQuery(name = "BankPO.findByCodeAndName", query = "SELECT b FROM BankPO b WHERE b.code = :code AND b.name = :name")})
public class BankPO extends CommonPO {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "code")
  private Integer code;
  
  @Column(name = "name")
  private String name;

  public BankPO() {
  }

  public BankPO(Integer code) {
    this.code = code;
  }

  public BankPO(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
