package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IAddBean;

/**
 *
 * @author ondrepe
 */
public abstract class ABean<T extends AbstractIFaceObject> implements IAddBean {
  
  private T item;
  
  protected abstract void addItem(T item) throws CommonIBException;
  protected abstract T initItem();
  
  @Override
  public final void add() {
    try {
     addItem(item);
     item = initItem();
    } catch(CommonIBException ex) {
    
    }
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
