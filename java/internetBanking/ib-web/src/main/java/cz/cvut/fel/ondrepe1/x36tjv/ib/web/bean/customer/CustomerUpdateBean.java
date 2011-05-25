package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class CustomerUpdateBean extends CustomerADBean implements AjaxBehaviorListener {
  
  private HtmlDataTable htmlDataTable;
  private boolean selected = true;
  
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

  public boolean isSelected() {
    return selected;
  }

  @Override
  public void processAjaxBehavior(AjaxBehaviorEvent abe) throws AbortProcessingException {
    Customer obj = (Customer) htmlDataTable.getRowData();
    setItem(obj);
    selected = false;
  }
}
