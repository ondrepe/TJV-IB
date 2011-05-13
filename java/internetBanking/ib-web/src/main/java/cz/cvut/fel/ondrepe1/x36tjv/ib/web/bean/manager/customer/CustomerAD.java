package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.ADLBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class CustomerAD extends ADLBean<Customer> {

  @EJB
  private ICustomerBean customerBean;
  private HtmlDataTable htmlDataTable;

  public HtmlDataTable getHtmlDataTable() {
    return htmlDataTable;
  }

  public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
    this.htmlDataTable = htmlDataTable;
  }
  
  @Override
  protected void addItem(Customer item) throws CommonIBException {
    customerBean.set(item);
  }

  @Override
  protected Customer initItem() {
    return new Customer();
  }

  @Override
  protected List<Customer> load() {
    return customerBean.getList();
  }

  @Override
  protected void deleteItem() throws CommonIBException {
    Integer id = Integer.parseInt(getSelectedItem());
    customerBean.delete(id);
  }
  
  public void setSelected(ValueChangeEvent event) {
    Object obj = htmlDataTable.getRowData();
    setItem((Customer) obj);
  }
  
}
