package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception;

/**
 *
 * @author ondrepe
 */
public class IBException extends RuntimeException {
   
  private IBExceptionCode code;
  
  public IBException(String message) {
    super(message);
    this.code = IBExceptionCode.UNKNOWN;
  }
  
  public IBException(IBExceptionCode code) {
    this.code = code;
  }
  
  public IBException(String message, IBExceptionCode code) {
    this(message);
    this.code = code;
  }
  
  public IBException(String message, Throwable cause) {
    super(message, cause);
    this.code = IBExceptionCode.UNKNOWN;
  }
  
  public IBException(IBExceptionCode code, Throwable cause) {
    super(cause);
    this.code = code;
  }
  
  public IBException(String message, IBExceptionCode code, Throwable cause) {
    this(message, cause);
    this.code = code;
  }

  public IBExceptionCode getCode() {
    return code;
  }
}
