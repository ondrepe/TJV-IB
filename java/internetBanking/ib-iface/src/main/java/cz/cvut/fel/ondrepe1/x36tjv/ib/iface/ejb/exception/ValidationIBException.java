package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception;

/**
 *
 * @author ondrepe
 */
public class ValidationIBException extends CommonIBException {

  private String errorAttribute;
  private String errorValue;
  
  public ValidationIBException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public ValidationIBException(String message) {
    super(message);
  }

  public String getErrorAttribute() {
    return errorAttribute;
  }

  public void setErrorAttribute(String errorAttribute) {
    this.errorAttribute = errorAttribute;
  }

  public String getErrorValue() {
    return errorValue;
  }

  public void setErrorValue(String errorValue) {
    this.errorValue = errorValue;
  }
  
}
