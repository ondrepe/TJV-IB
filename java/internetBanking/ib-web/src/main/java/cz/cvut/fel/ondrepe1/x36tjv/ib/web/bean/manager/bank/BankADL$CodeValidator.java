package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.bank;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class BankADL$CodeValidator implements Validator {

  private static final String MANDATORY = "Kód je povinný!";
  private static final String BAD_VALUE = "Kód musí být trojciferné celé kladné číslo!";
  private static final String USED = "Kód už je použit!";

  @EJB
  private IBankCodeBean bankBean;
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    Integer intValue = (Integer) value;
    if (intValue < 100 || intValue > 999) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
    if(exist(intValue)) {
      throw new ValidatorException(new FacesMessage(USED));
    }
  }
  
  private boolean exist(int code) {
    return bankBean.existBankCode(code);
  }
}
