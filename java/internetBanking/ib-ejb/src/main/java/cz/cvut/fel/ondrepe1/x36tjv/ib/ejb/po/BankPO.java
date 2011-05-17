package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "bank")
@NamedQueries({
  @NamedQuery(name = "BankPO.findAll", query = "SELECT b FROM BankPO b"),
  @NamedQuery(name = "BankPO.findByName", query = "SELECT b FROM BankPO b WHERE b.name = :name"),
  @NamedQuery(name = "BankPO.findByCodeAndName", query = "SELECT b FROM BankPO b WHERE b.code = :code AND b.name = :name")})
public class BankPO implements Serializable {

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
