package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.CommonTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;

/**
 *
 * @author ondrepe
 */
public class CurrencyRateTranslator extends CommonTranslator<CurrentCurrencyRatePO, CurrencyRate> {

  @Override
  public CurrencyRate translate(CurrentCurrencyRatePO from) {
    CurrencyRate currencyRate = new CurrencyRate();
    
    currencyRate.setCode(from.getCode());
    currencyRate.setRate(from.getRate());
    
    return currencyRate;
  }
  
}
