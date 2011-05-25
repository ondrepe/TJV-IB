package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.statement;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IStatementBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonListBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class StatementSend extends CommonListBean<Account> {

  @EJB
  private IAccountBean accountBean;
  @EJB
  private IStatementBean statementBean;
  
  private Integer accountId;
  
  @Override
  protected List<Account> load() {
    return accountBean.getList();
  }
  
  public void send() {
    statementBean.sendStatement(accountId);
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }
  
}
