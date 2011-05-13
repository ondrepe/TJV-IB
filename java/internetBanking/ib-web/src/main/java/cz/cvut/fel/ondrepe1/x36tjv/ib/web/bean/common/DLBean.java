package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class DLBean <T extends AbstractIFaceObject> implements IListBean<T>, IDeleteBean {
  
  private String selectedItem;
  private List<T> list;
  
  protected abstract List<T> load();
  protected abstract void deleteItem();
  
  @Override
  public final List<T> getList() {
    if(this.list == null) {
      this.list = load();
    }
    return this.list;
  }

  public void setSelectedItem(String selectedItem) {
    this.selectedItem = selectedItem;
  }

  public String getSelectedItem() {
    return selectedItem;
  }
  
  public final void reload() {
    this.list = load();
  }
  
  @Override
  public final void delete() {
    deleteItem();
    reload();
  }
  
}
