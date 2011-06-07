package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IAddBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;

/**
 *
 * @author ondrepe
 */
public abstract class CommonAddBean<T extends CommonTO> extends CommonBean implements IAddBean {
  
  private T item;
  
  protected abstract T initItem();
  protected abstract void addItem(T item);
  protected void customInit() {
  }
  
  @PostConstruct
  public void init() {
    item = initItem();
    customInit();
  }
  
  @Override
  public final void add() {
    try {
     addItem(item);
     init();
    } catch(EJBException ex) {
      handleEJBException(ex);
    }
  }

  public void setItem(T item) {
    this.item = item;
  }

  public T getItem() {
    return item;
  }
}
