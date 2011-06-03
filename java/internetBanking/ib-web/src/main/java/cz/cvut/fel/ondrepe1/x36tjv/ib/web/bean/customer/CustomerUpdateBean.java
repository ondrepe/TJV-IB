package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.AbortProcessingException;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class CustomerUpdateBean extends CustomerADBean {
  
  private HtmlDataTable htmlDataTable;
  private boolean selected = true;
  
  public HtmlDataTable getHtmlDataTable() {
    return htmlDataTable;
  }

  public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
    this.htmlDataTable = htmlDataTable;
  }

  public boolean isSelected() {
    return selected;
  }

  public void selectForUpdate() throws AbortProcessingException {
    Customer obj = (Customer) htmlDataTable.getRowData();
    setItem(obj);
    selected = false;
  }
}
