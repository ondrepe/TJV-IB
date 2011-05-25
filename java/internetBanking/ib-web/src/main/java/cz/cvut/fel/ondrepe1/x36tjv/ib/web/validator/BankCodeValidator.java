package cz.cvut.fel.ondrepe1.x36tjv.ib.web.validator;

import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.bank.*;
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
public class BankCodeValidator implements Validator {

  private static final String MANDATORY = "Code is mandatory!";
  private static final String BAD_VALUE = "Code must be a three-digit positive number!";
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    Integer intValue = (Integer) value;
    if (intValue < 100 || intValue > 999) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
  }
}
