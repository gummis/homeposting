package homeposting.app.web.transactions.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name = "transactionsManagerBean", eager = true)
@ViewScoped
@URLMapping(id = "transactionsManagerId", pattern = "/transactions/manager", viewId = "/views/transactions/manager/transactionsManager.xhtml")
public class TransactionsManagerBean {

}
