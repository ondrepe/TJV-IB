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
@Table(name = "globalparam")
@NamedQueries({
  @NamedQuery(name = "GlobalParamPO.findAll", query = "SELECT g FROM GlobalParamPO g")})
public class GlobalParamPO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "code")
  private String code;
  
  @Column(name = "value")
  private String value;
  
  @Column(name = "dataType")
  private String dataType;

  public GlobalParamPO() {
  }

  public GlobalParamPO(String code) {
    this.code = code;
  }

  public GlobalParamPO(String code, String value, String dataType) {
    this.code = code;
    this.value = value;
    this.dataType = dataType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }
}
