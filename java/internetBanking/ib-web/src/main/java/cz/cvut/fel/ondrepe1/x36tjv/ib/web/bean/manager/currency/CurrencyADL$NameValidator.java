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
public class CurrencyADL$NameValidator implements Validator {

  private static final String MANDATORY = "Jméno je povinné!";
  private static final String ONLY_SPACES = "Jméno musí obsahovat viditelné znaky!";
  private static final String USED = "Jméno je použito!";

  @EJB
  private ICurrencyCodeBean bankBean;
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      throw new ValidatorException(new FacesMessage(MANDATORY));
    }
    String stringValue = (String) value;
    if (stringValue.trim().isEmpty()) {
      throw new ValidatorException(new FacesMessage(ONLY_SPACES));
    }
    if(exist(stringValue)) {
      throw new ValidatorException(new FacesMessage(USED));
    }
  }
  
  private boolean exist(String name) {
    return bankBean.existCurrencyName(name);
  }
}
