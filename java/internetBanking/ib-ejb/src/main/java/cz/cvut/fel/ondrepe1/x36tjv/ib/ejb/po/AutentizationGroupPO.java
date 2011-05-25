package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "autentizationgroup")
public class AutentizationGroupPO extends CommonPO {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "groupName")
  private String group;

  public AutentizationGroupPO() {
  }

  public AutentizationGroupPO(String group) {
    this.group = group;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }
  
}
