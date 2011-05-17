package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
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
public class CustomerU extends CustomerAD {
  
  private HtmlDataTable htmlDataTable;

  public HtmlDataTable getHtmlDataTable() {
    return htmlDataTable;
  }

  public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
    this.htmlDataTable = htmlDataTable;
  }
  
  public void setSelected(ValueChangeEvent event) {
    Object obj = htmlDataTable.getRowData();
    setItem((Customer) obj);
  }
  
}
