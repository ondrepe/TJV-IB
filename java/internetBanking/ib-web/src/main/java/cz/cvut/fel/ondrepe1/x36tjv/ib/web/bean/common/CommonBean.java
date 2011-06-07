package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ondrepe
 */
public abstract class CommonBean {

  public void handleEJBException(EJBException ex) {
    if (ex != null) {
      Exception cException = ex.getCausedByException();
      if (cException != null && cException instanceof IBException) {
        IBException ibException = (IBException) cException;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ibException.getCode().name() + ": " + ibException.getMessage()));
      }
    }
  }
}
