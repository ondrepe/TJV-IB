package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.currency.*;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
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
public class CurrencyRateADL$RateValidator implements Validator {

  private static final String MANDATORY = "Sazba je povinná!";
  private static final String BAD_VALUE = "Sazba musí být nezáporné číslo s nejvýše dvěma číslicemi za desetinnou čárkou!";

  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    Double doubleValue = (Double) value;
    if (doubleValue < 0 || doubleValue > 99.99 || !(doubleValue.toString()).matches("(\\d{0,2}|\\d{0,2}[\\.,\\,]\\d{0,2})")) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
  }
}
