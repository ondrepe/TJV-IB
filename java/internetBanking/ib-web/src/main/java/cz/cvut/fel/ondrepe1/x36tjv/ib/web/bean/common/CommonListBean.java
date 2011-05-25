package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.iface.IListBean;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class CommonListBean<T extends CommonTO> implements IListBean<T> {

  private List<T> list;
  private boolean renderList;
  private boolean isLoaded;

  protected abstract List<T> load();

  private void loadInternal() {
    list = load();
    if (list.isEmpty()) {
      renderList = false;
    } else {
      renderList = true;
    }
    isLoaded = true;
  }

  @Override
  public final List<T> getList() {
    if (!isLoaded) {
      loadInternal();
    }
    return this.list;
  }

  public final void reload() {
    loadInternal();
  }

  public boolean isRenderList() {
    loadInternal();
    return renderList;
  }
}
