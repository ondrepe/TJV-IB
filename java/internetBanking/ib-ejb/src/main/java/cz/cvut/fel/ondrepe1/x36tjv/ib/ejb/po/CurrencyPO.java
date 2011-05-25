package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author ondrepe
 */
@Entity
@Cache(alwaysRefresh=true)
@Table(name = "currency")
@NamedQueries({
  @NamedQuery(name = "CurrencyPO.findAll", query = "SELECT c FROM CurrencyPO c"),
  @NamedQuery(name = "CurrencyPO.findByCodeAndName", query = "SELECT c FROM CurrencyPO c WHERE c.code = :code AND c.name = :name"),
  @NamedQuery(name = "CurrencyPO.findByCode", query = "SELECT c FROM CurrencyPO c WHERE c.code = :code"),
  @NamedQuery(name = "CurrencyPO.findByCodeAndNameAndDD", query = "SELECT c FROM CurrencyPO c WHERE c.code = :code AND c.name = :name AND c.decimalDigits = :decimalDigits"),
  @NamedQuery(name = "CurrencyPO.findByName", query = "SELECT c FROM CurrencyPO c WHERE c.name = :name"),
  @NamedQuery(name = "CurrencyPO.findByDecimalDigits", query = "SELECT c FROM CurrencyPO c WHERE c.decimalDigits = :decimalDigits")})
public class CurrencyPO extends CommonPO {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "code")
  private String code;
  
  @Size(min = 1, max = 45)
  @Column(name = "name")
  private String name;
  
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
}
