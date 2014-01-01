package homeposting.app.ejb;

import homeposting.app.common.data.Shortcut;
import homeposting.app.domain.entities.Account;
import homeposting.app.domain.entities.Subsystem;
import homeposting.app.domain.entities.TransactionKind;
import homeposting.app.domain.entities.TransactionSubkind;

import java.util.List;

import javax.ejb.Local;

@Local
public interface SubsystemsDao {
	public void createSubsystem(Subsystem subsystem);
	public List<Shortcut> getAllSubsystemsShortuctsOfUser(Integer id);
	public Subsystem getSubsystemById(Integer id);
	public void addUserToSubsystem(Integer idAddedUser, Integer id);
	public void addAccountToSubsystem(Subsystem subsystem, Account account);
	public void addTransactionKindToSubsystem(Subsystem subsystem, TransactionKind kind);
	public void addTransactionSubkindToSubsystem(Subsystem subsystem, Integer transactionKindId, TransactionSubkind subkind);
	public void removeTransactionSubkindFromSubsystem(Integer id,Integer transactionKindId, Integer transactionSubkindId);
}
