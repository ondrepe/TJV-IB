package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonListBean;
import java.util.List;
import javax.ejb.EJB;
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
public class AccountV extends CommonListBean<Account> {

  @EJB
  private IAccountBean accountBean;
  
  @Override
  protected List<Account> load() {
    HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String value=request.getParameter("id");
    if(value !=null && !value.trim().isEmpty()) {
      try {
        Integer customerId = Integer.parseInt(value);
        return accountBean.getListByCustomerId(customerId);
      } catch(Exception e) {}
    }
    return accountBean.getList();
  }
  
}
