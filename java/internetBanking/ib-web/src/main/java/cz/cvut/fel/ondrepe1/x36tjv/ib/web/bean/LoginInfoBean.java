package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAuthentizationBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class LoginInfoBean {

  @EJB
  private IAuthentizationBean autentizationBean;
  private boolean manager;
  private boolean customer;
  private String label;
  private String name;
  private String user;

  public LoginInfoBean() {
    setLoginData();
  }

  public boolean isCustomer() {
    return customer;
  }

  public void setCustomer(boolean customer) {
    this.customer = customer;
  }

  public boolean isManager() {
    return manager;
  }

  public void setManager(boolean manager) {
    this.manager = manager;
  }

  private void setLoginData() {
    manager = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("MANAGER");
    customer = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("CUSTOMER");
    user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    if (manager) {
      label = "Internet Banking for Managers";
      name = user;
    } else {
      label = "Internet Banking for Customers";
    }
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getName() {
    if(customer) {
      Customer cstmr = autentizationBean.getLoggedCustomer();
      name = cstmr.getFirstName() + " " + cstmr.getLastName();
    }
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String logout() {
    HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    sess.invalidate();
    return "logout";
  }
}
