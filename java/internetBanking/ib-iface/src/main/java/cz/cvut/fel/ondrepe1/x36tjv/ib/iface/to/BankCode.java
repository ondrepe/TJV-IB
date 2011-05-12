package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

/**
 *
 * @author ondrepe
 */
public class BankCode extends AbstractIFaceObject {

  private Integer code;
  private String name;

  public BankCode() {
  }

  public BankCode(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }
}
