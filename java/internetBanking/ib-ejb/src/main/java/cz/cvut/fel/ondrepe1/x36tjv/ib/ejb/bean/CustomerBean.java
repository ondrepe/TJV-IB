package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class CustomerBean implements ICustomerBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;
  
  @Override
  @RolesAllowed({"MANAGER"})
  public List<Customer> getList() {
    ListCommand command = new CustomerListCommand(em, ctx);
    return command.execute();
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void set(Customer cstmr) {
    SetCommand command = new CustomerSetCommand(em, ctx);
    command.execute(cstmr);
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void delete(int i) {
    DeleteCommand command = new CustomerDeleteCommand(em, ctx);
    command.execute(new Integer(i));
  }
  
}
