package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.CommonTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;

/**
 *
 * @author ondrepe
 */
public class CurrencyTranslator extends CommonTranslator<CurrencyPO, CurrencyCode> {

  @Override
  public CurrencyCode translate(CurrencyPO from) {
    CurrencyCode currencyCode = new CurrencyCode();
    
    currencyCode.setCode(from.getCode());
    currencyCode.setName(from.getName());
    currencyCode.setDecimalDigits(from.getDecimalDigits());
    
    return currencyCode;
  }
  
}
