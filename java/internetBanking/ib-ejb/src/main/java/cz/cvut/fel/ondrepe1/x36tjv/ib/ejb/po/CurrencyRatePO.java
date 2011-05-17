package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
  @Column(name = "code")
  private String code;
  
  @Column(name = "rate")
  private BigDecimal rate;
  
  @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private CurrencyPO currency;
  
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "currencyRate")
  private CurrentCurrencyRatePO currentCurrencyRate;

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

  public CurrencyPO getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyPO currency) {
    this.currency = currency;
  }

  public CurrentCurrencyRatePO getCurrentCurrencyRate() {
    return currentCurrencyRate;
  }

  public void setCurrentCurrencyRate(CurrentCurrencyRatePO currentCurrencyRate) {
    this.currentCurrencyRate = currentCurrencyRate;
  }
}
