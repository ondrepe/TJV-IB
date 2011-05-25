package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public interface IListBean<T extends CommonTO> {
  
  public List<T> getList();
}
