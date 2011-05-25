package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IListBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IDeleteBean;

/**
 *
 * @author ondrepe
 */
public abstract class CommonDLBean <T extends CommonTO> extends CommonListBean<T> implements IListBean<T>, IDeleteBean {
  
  private String selectedItem;
  private boolean renderDelete;
  
  protected abstract void deleteItem();

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
    } catch(IBException ex) {
    
    }
  }

  public void setRenderDelete(boolean renderDelete) {
    this.renderDelete = renderDelete;
  }
  
  public boolean isRenderDelete() {
    return renderDelete;
  }
}
