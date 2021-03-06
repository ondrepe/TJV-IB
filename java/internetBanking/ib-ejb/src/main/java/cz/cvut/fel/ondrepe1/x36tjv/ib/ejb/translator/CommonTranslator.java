package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CommonPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class CommonTranslator<PO extends CommonPO, TO extends CommonTO> implements ITranslator<PO, TO> {

  public List<TO> translateList(List<PO> from) {
    ArrayList<TO> to = new ArrayList<TO>();
    for (PO po : from) {
      to.add(translate(po));
    }
    return to;
  }
}
