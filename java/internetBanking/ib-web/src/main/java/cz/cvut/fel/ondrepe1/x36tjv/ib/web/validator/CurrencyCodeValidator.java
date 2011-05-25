package cz.cvut.fel.ondrepe1.x36tjv.ib.web.validator;

import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.currency.*;
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
public class CurrencyCodeValidator implements Validator {

  private static final String MANDATORY = "The code is mandatory!";
  private static final String BAD_VALUE = "The code must be 3 characters!";
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    String stringValue = (String) value;
    if (!stringValue.matches("^[A-Z,a-z]{3}$")) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
  }
}
