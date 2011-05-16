package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IListBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IDeleteBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class DLBean <T extends AbstractIFaceObject> extends LBean<T> implements IListBean<T>, IDeleteBean {
  
  private String selectedItem;
  
  protected abstract void deleteItem() throws CommonIBException;

  public void setSelectedItem(String selectedItem) {
    this.selectedItem = selectedItem;
  }

  public String getSelectedItem() {
    return selectedItem;
  }
  
  @Override
  public final void delete() {
    try {
     deleteItem();
     reload();
    } catch(CommonIBException ex) {
    
    }
  }
  
}
