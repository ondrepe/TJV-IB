package cz.cvut.fel.ondrepe1.x36tjv.ib.web.validator;

import java.math.BigDecimal;
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
public class AccountBalanceValidator implements Validator {

  private static final String MANDATORY = "Balance is mandatory!";
  private static final String BAD_VALUE = "The balance must be positive number!";

  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    BigDecimal bigDecValue = (BigDecimal) value;
    int compare = bigDecValue.compareTo(BigDecimal.ZERO);
    if (compare <= 0) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
  }
  
}
