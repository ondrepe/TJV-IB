package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonADLBean;
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
public class CustomerADBean extends CommonADLBean<Customer> {

  @EJB
  private ICustomerBean customerBean;
  
  @Override
  protected void addItem(Customer item) {
    customerBean.set(item);
  }

  @Override
  protected Customer initItem() {
    return new Customer();
  }

  @Override
  protected List<Customer> load() {
    List<Customer> list = customerBean.getList();
    setRenderDelete(!list.isEmpty());
    return list;
  }

  @Override
  protected void deleteItem() {
    Integer id = Integer.parseInt(getSelectedItem());
    customerBean.delete(id);
  }
  
}
