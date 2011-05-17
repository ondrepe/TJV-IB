package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "autentizationgroup")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "AutentizationGroupPO.findAll", query = "SELECT a FROM AutentizationGroupPO a"),
  @NamedQuery(name = "AutentizationGroupPO.findByGroup", query = "SELECT a FROM AutentizationGroupPO a WHERE a.group = :group")})
public class AutentizationGroupPO implements Serializable {
  
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
