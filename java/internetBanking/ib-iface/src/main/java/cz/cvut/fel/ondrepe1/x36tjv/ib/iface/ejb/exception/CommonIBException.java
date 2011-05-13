package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception;

/**
 *
 * @author ondrepe
 */
public class CommonIBException extends Exception {
   
  private String exceptionMessage;

  public CommonIBException(String message) {
    super(message);
    exceptionMessage = message;
  }
  
  public CommonIBException(String message, Throwable throwable) {
    super(message, throwable);
    exceptionMessage = message;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  public void setExceptionMessage(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }
}
