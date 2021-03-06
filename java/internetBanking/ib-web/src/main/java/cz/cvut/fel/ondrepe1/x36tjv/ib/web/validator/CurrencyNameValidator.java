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
public class CurrencyNameValidator implements Validator {

  private static final String MANDATORY = "Name is mandatory!";
  private static final String BAD_VALUE = "Name must be non-empty string!";
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    String stringValue = (String) value;
    if (stringValue.trim().isEmpty() || stringValue.trim().length() > 250) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
  }
}
