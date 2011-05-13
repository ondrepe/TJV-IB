package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception;

/**
 *
 * @author ondrepe
 */
public class EntityExistIBException extends CommonIBException {
  
  public EntityExistIBException(String entity) {
    super("Entity " + entity + " already exist");
  }
  
}
