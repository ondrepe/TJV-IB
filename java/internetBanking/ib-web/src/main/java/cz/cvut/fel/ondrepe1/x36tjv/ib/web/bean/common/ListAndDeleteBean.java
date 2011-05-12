package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class ListAndDeleteBean <T extends AbstractIFaceObject> {
  
  private String selectedItem;
  private List<T> list;
  
  protected abstract List<T> load();
  public abstract void delete();
  
  public final List<T> getList() {
    if(this.list == null) {
      this.list = load();
    }
    return this.list;
  }

  public void setSelectedItem(String selectedItem) {
    this.selectedItem = selectedItem;
  }

  public Object getSelectedItem() {
    return selectedItem;
  }
  
}
