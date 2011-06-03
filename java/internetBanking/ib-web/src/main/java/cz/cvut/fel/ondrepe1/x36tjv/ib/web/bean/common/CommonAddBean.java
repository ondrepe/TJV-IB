package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IAddBean;
import javax.annotation.PostConstruct;

/**
 *
 * @author ondrepe
 */
public abstract class CommonAddBean<T extends CommonTO> implements IAddBean {
  
  private T item;
  
  protected abstract T initItem();
  protected abstract void addItem(T item);
  
  @PostConstruct
  public void init() {
    item = initItem();
  }
  
  @Override
  public final void add() {
    try {
     addItem(item);
     init();
    } catch(IBException ex) {
    
    }
  }

  public void setItem(T item) {
    this.item = item;
  }

  public T getItem() {
    return item;
  }
}
