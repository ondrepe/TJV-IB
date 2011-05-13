package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "currencyrate")
@NamedQueries({
  @NamedQuery(name = "CurrencyRatePO.findAll", query = "SELECT c FROM CurrencyRatePO c"),
  @NamedQuery(name = "CurrencyRatePO.findByCodeAndRate", query = "SELECT c FROM CurrencyRatePO c WHERE c.code = :code AND c.rate = :rate"),
  @NamedQuery(name = "CurrencyRatePO.findByCode", query = "SELECT c FROM CurrencyRatePO c WHERE c.code = :code"),
  @NamedQuery(name = "CurrencyRatePO.findByRate", query = "SELECT c FROM CurrencyRatePO c WHERE c.rate = :rate")})
public class CurrencyRatePO implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "code")
  private String code;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "rate")
  private BigDecimal rate;
  @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private CurrencyPO currencyPO;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "currencyRatePO")
  private CurrentCurrencyRatePO currentCurrencyRatePO;

  public CurrencyRatePO() {
  }

  public CurrencyRatePO(String code) {
    this.code = code;
  }

  public CurrencyRatePO(String code, BigDecimal rate) {
    this.code = code;
    this.rate = rate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public CurrencyPO getCurrencyPO() {
    return currencyPO;
  }

  public void setCurrencyPO(CurrencyPO currencyPO) {
    this.currencyPO = currencyPO;
  }

  public CurrentCurrencyRatePO getCurrentCurrencyRatePO() {
    return currentCurrencyRatePO;
  }

  public void setCurrentCurrencyRatePO(CurrentCurrencyRatePO currentCurrencyRatePO) {
    this.currentCurrencyRatePO = currentCurrencyRatePO;
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
    if (!(object instanceof CurrencyRatePO)) {
      return false;
    }
    CurrencyRatePO other = (CurrencyRatePO) object;
    if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO[ code=" + code + " ]";
  }
  
}
