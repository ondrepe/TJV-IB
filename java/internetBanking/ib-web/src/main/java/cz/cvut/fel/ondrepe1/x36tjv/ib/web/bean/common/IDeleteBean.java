package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;

/**
 *
 * @author ondrepe
 */
public interface IDeleteBean<T extends AbstractIFaceObject> extends IListBean<T> {
  
  public String delete();
}
