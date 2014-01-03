package homeposting.app.web.accounts.manager;

import homeposting.app.common.data.util.Pair;
import homeposting.app.common.security.Security;
import homeposting.app.domain.entities.Account;
import homeposting.app.ejb.AccountsDao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;


@ManagedBean(name = "accountsManagerBean", eager = true)
@ViewScoped
@URLMapping(id = "accountsManagerId", pattern = "/accounts/manager", viewId = "/views/accounts/manager/accountsManager.xhtml")
public class AccountsManagerBean {

	public AccountsManagerBean() {
		Security.logged();
	}

	@EJB
	AccountsDao accountsDao;

	public List<Pair<Account,Integer>> getAccountsInfo() {
		return accountsDao.getAccountsInfo();
	}
}

