package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AutentizationPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CommonCommand {

  protected EntityManager em;
  protected SessionContext ctx;
  private boolean managerLogged = false;
  private boolean customerLogged = false;
  private CustomerPO customer;

  public CommonCommand(EntityManager em) {
    this.em = em;
  }

  public CommonCommand(EntityManager em, SessionContext ctx) {
    this.em = em;
    this.ctx = ctx;
    this.initPermissions();
  }

  protected abstract boolean authorize();

  private void initPermissions() {
    managerLogged = ctx.isCallerInRole("MANAGER");
    customerLogged = ctx.isCallerInRole("CUSTOMER");

    if (customerLogged) {
      String login = ctx.getCallerPrincipal().getName();
      AutentizationPO autentization = em.find(AutentizationPO.class, login);
      customer = autentization.getCustomer();
    }
  }

  public boolean isManager() {
    return managerLogged;
  }

  public boolean isCustomer() {
    return customerLogged;
  }

  public CustomerPO getCustomer() {
    return customer;
  }
}
