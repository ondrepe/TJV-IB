package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.LBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class CustomerV extends LBean<Customer> {

  @EJB
  private ICustomerBean customerBean;
  
  @Override
  protected List<Customer> load() {
    return customerBean.getList();
  }
  
}
