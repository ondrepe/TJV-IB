package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.currency;

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
public class CurrencyADL$CodeValidator implements Validator {

  private static final String MANDATORY = "Kód je povinný!";
  private static final String BAD_VALUE = "Kód musí být tři znaky!";
  private static final String USED = "Kód už je použit!";

  @EJB
  private ICurrencyCodeBean bankBean;
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    String stringValue = (String) value;
    if (!stringValue.matches("^[A-Z,a-z]{3}$")) {
      throw new ValidatorException(new FacesMessage(BAD_VALUE));
    }
    if(exist(stringValue)) {
      throw new ValidatorException(new FacesMessage(USED));
    }
  }
  
  private boolean exist(String code) {
    return bankBean.existCurrencyCode(code);
  }
}
