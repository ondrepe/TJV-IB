package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "bank")
@NamedQueries({
  @NamedQuery(name = "BankPO.findAll", query = "SELECT b FROM BankPO b"),
  @NamedQuery(name = "BankPO.findByCode", query = "SELECT b FROM BankPO b WHERE b.code = :code"),
  @NamedQuery(name = "BankPO.findByName", query = "SELECT b FROM BankPO b WHERE b.name = :name")})
public class BankPO implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name")
  private String name;
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "code")
  private Integer code;

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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (code != null ? code.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof BankPO)) {
      return false;
    }
    BankPO other = (BankPO) object;
    if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO[ code=" + code + " ]";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
