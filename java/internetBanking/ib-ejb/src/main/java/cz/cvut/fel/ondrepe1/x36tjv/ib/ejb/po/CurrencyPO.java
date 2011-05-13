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
@Table(name = "currency")
@NamedQueries({
  @NamedQuery(name = "CurrencyPO.findAll", query = "SELECT c FROM CurrencyPO c"),
  @NamedQuery(name = "CurrencyPO.findByCode", query = "SELECT c FROM CurrencyPO c WHERE c.code = :code"),
  @NamedQuery(name = "CurrencyPO.findByCodeAndNameAndDD", query = "SELECT c FROM CurrencyPO c WHERE c.code = :code AND c.name = :name AND c.decimalDigits = :decimalDigits"),
  @NamedQuery(name = "CurrencyPO.findByName", query = "SELECT c FROM CurrencyPO c WHERE c.name = :name"),
  @NamedQuery(name = "CurrencyPO.findByDecimalDigits", query = "SELECT c FROM CurrencyPO c WHERE c.decimalDigits = :decimalDigits")})
public class CurrencyPO implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "code")
  private String code;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @NotNull
  @Column(name = "decimalDigits")
  private int decimalDigits;

  public CurrencyPO() {
  }

  public CurrencyPO(String code) {
    this.code = code;
  }

  public CurrencyPO(String code, String name, int decimalDigits) {
    this.code = code;
    this.name = name;
    this.decimalDigits = decimalDigits;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDecimalDigits() {
    return decimalDigits;
  }

  public void setDecimalDigits(int decimalDigits) {
    this.decimalDigits = decimalDigits;
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
    if (!(object instanceof CurrencyPO)) {
      return false;
    }
    CurrencyPO other = (CurrencyPO) object;
    if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO[ code=" + code + " ]";
  }
  
}
