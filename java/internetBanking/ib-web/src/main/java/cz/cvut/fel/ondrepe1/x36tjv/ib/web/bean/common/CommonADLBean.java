package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IAddBean;

/**
 *
 * @author ondrepe
 */
public abstract class CommonADLBean<T extends CommonTO> extends CommonDLBean<T> implements IAddBean {
  
  private T item;
  
  protected abstract void addItem(T item);
  protected abstract T initItem();
  
  @Override
  public final void add() {
    try {
     addItem(item);
     item = initItem();
     reload();
    } catch(IBException ex) {
    
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
