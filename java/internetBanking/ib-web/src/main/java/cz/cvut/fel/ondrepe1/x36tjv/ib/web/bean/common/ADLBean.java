package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;

/**
 *
 * @author ondrepe
 */
public abstract class ADLBean<T extends AbstractIFaceObject> extends DLBean<T> implements IAddBean {
  
  private T item;
  
  protected abstract void addItem(T item);
  protected abstract T initItem();
  
  @Override
  public final void add() {
    addItem(item);
    reload();
  }

  public void setItem(T item) {
    this.item = item;
  }

  public T getItem() {
    if(item == null) {
      item = initItem();
    }
    return item;
  }
  
}
