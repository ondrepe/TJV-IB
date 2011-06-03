package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IListBean;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author ondrepe
 */
public abstract class CommonListBean<T extends CommonTO> implements IListBean<T> {

  private List<T> list;
  private boolean renderList;

  protected abstract List<T> load();
  protected void customInit() {
  }

  @PostConstruct
  public void init() {
    list = load();
    if (list.isEmpty()) {
      renderList = false;
    } else {
      renderList = true;
    }
    customInit();
  }

  @Override
  public final List<T> getList() {
    return this.list;
  }

  public boolean isRenderList() {
    return renderList;
  }
}
