package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public interface IListBean<T extends AbstractIFaceObject> {
  
  public List<T> getList();
}
