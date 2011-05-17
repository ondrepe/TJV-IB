package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "idtable")
public class AccountNumberPO implements Serializable {
  
  @Id
  @Column(name = "name")
  private String name;
  
  @Column(name = "val")
  private Integer val;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getVal() {
    return val;
  }

  public void setVal(Integer val) {
    this.val = val;
  }
}
