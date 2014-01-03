package homeposting.app.ejb;

import homeposting.app.common.data.util.Pair;
import homeposting.app.domain.entities.Account;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AccountsDao {
	public List<Pair<Account,Integer>> getAccountsInfo();
}
