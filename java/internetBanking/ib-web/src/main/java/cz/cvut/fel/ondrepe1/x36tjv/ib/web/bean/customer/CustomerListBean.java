package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonListBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class CustomerListBean extends CommonListBean<Customer> {

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
  protected List<Customer> load() {
    return customerBean.getList();
  }
  
}
