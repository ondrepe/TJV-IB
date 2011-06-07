package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ITransactionBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonListBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class TransactionListBean extends CommonListBean<Transaction> {

  @EJB
  private ITransactionBean transactionBean;
  
  private boolean id;
  
  @Override
  protected List<Transaction> load() {
    HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String value=request.getParameter("id");
    if(value !=null && !value.trim().isEmpty()) {
      try {
        Integer accountId = Integer.parseInt(value);
        id = true;
        return transactionBean.getListByAccountId(accountId);
      } catch(EJBException e) {
        handleEJBException(e);
      }
    }
    id = false;
    return transactionBean.getList();
  }

  public boolean isId() {
    return id;
  }

  public void setId(boolean id) {
    this.id = id;
  }
}
