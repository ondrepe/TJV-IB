package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CommonPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;

/**
 *
 * @author ondrepe
 */
public interface ITranslator<PO extends CommonPO, TO extends CommonTO> {

  public TO translate(PO from);
}
