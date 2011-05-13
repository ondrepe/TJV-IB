package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.bank;

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
public class BankADL$NameValidator implements Validator {

  private static final String MANDATORY = "Name is mandatory!";
  private static final String ONLY_SPACES = "Name must be non-empty string!";
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    String stringValue = (String) value;
    if (stringValue.trim().isEmpty() || stringValue.trim().length() > 250) {
      throw new ValidatorException(new FacesMessage(ONLY_SPACES));
    }
  }
}
