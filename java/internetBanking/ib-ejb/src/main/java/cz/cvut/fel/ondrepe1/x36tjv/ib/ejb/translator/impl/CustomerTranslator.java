package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.CommonTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;

/**
 *
 * @author ondrepe
 */
public class CustomerTranslator extends CommonTranslator<CustomerPO, Customer> {

  @Override
  public Customer translate(CustomerPO from) {
    Customer customer = new Customer();
    
    customer.setId(from.getId());
    customer.setFirstName(from.getFirstName());
    customer.setLastName(from.getLastName());
    customer.setEmail(from.getEmail());
    
    return customer;
  }  
}
