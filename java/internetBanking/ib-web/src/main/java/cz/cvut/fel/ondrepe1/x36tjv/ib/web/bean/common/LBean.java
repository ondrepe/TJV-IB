package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class LBean <T extends AbstractIFaceObject> implements IListBean<T> {
  
  private List<T> list;
  
  protected abstract List<T> load();
  
  @Override
  public final List<T> getList() {
    if(this.list == null) {
      this.list = load();
    }
    return this.list;
  }
  
  public final void reload() {
    this.list = load();
  }
 
}
