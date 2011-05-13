package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.ADLBean;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public class CustomerAD extends ADLBean<Customer> {

  @Override
  protected void addItem(Customer item) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Customer initItem() {
    return new Customer();
  }

  @Override
  protected List<Customer> load() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected void deleteItem() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}
