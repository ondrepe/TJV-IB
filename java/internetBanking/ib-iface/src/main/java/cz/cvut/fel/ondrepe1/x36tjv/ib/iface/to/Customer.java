package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

/**
 *
 * @author ondrepe
 */
public class Customer extends AbstractIFaceObject {
  
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;

  public Customer() {
  }

  public Customer(Integer id, String firstName, String lastName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
}
