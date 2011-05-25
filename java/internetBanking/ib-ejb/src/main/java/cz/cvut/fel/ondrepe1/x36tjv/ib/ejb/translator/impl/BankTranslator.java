package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.CommonTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;

/**
 *
 * @author ondrepe
 */
public class BankTranslator extends CommonTranslator<BankPO, BankCode> {

  @Override
  public BankCode translate(BankPO from) {
    BankCode bankCode = new BankCode();
    
    bankCode.setCode(from.getCode());
    bankCode.setName(from.getName());
    
    return bankCode;
  }
  
}
