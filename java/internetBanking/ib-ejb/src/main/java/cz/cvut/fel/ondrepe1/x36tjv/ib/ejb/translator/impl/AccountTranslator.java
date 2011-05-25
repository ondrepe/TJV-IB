package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.CommonTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;

/**
 *
 * @author ondrepe
 */
public class AccountTranslator extends CommonTranslator<AccountPO, Account> {

  @Override
  public Account translate(AccountPO from) {
    Account account = new Account();
    
    account.setId(from.getId());
    account.setCurrencyCode(from.getCurrency().getCode());
    account.setAccountNumber(from.getAccountNumber());
    account.setBalance(from.getBalance());
    
    return account;
  }
}
