package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "currentcurrencyrate")
@NamedQueries({
  @NamedQuery(name = "CurrentCurrencyRatePO.findAll", query = "SELECT c FROM CurrentCurrencyRatePO c"),
  @NamedQuery(name = "CurrentCurrencyRatePO.findByCode", query = "SELECT c FROM CurrentCurrencyRatePO c WHERE c.code = :code"),
  @NamedQuery(name = "CurrentCurrencyRatePO.findByRate", query = "SELECT c FROM CurrentCurrencyRatePO c WHERE c.rate = :rate")})
public class CurrentCurrencyRatePO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "code")
  private String code;
  
  @Column(name = "rate")
  private BigDecimal rate;
  
  @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private CurrencyRatePO currencyRate;

  public CurrentCurrencyRatePO() {
  }

  public CurrentCurrencyRatePO(String code) {
    this.code = code;
  }

  public CurrentCurrencyRatePO(String code, BigDecimal rate) {
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

  public CurrencyRatePO getCurrencyRate() {
    return currencyRate;
  }

  public void setCurrencyRate(CurrencyRatePO currencyRate) {
    this.currencyRate = currencyRate;
  }
}
