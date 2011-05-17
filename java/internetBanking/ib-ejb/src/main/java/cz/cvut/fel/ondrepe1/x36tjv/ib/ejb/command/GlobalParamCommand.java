package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.GlobalParamPO;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class GlobalParamCommand extends CommonCommand {

  public GlobalParamCommand(EntityManager em) {
    super(em);
  }
  
  public Integer getNumberParam(String paramCode) {
    GlobalParamPO param = em.find(GlobalParamPO.class, paramCode);
    if(param == null || !param.getDataType().equalsIgnoreCase("N"))
      return null;
    
    Integer intParam = Integer.parseInt(param.getValue());
    return intParam;
  }
}
