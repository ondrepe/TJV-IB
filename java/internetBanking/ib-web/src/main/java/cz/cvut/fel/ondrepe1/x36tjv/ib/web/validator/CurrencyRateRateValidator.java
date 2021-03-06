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
public class CurrencyRateRateValidator implements Validator {

  private static final String MANDATORY = "Rate is mandatory!";
  private static final String BAD_VALUE = "The rate must be nonnegative with a maximum of two digits after the decimal point!";

  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    BigDecimal bigDecValue = (BigDecimal) value;
    int compareZero = bigDecValue.compareTo(BigDecimal.ZERO);
    int compareMax = bigDecValue.compareTo(new BigDecimal("99.99"));
    
    if (compareZero <= 0 || compareMax > 0 || !(bigDecValue.toString()).matches("(\\d{0,2}|\\d{0,2}[\\.,\\,]\\d{0,2})")) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
  }
}
