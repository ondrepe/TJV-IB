package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerGetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAuthentizationBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ondrepe
 */
@Stateless
@DeclareRoles({"MANAGER", "CUSTOMER"})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AutentizationBean implements IAuthentizationBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;

  @Override
  @RolesAllowed({"CUSTOMER"})
  public Customer getLoggedCustomer() {
    GetCommand<CustomerPO, Customer> command = new CustomerGetCommand(em, ctx);
    return command.execute(-1);
  }
}
