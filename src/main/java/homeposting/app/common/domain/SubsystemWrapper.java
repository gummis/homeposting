package homeposting.app.common.domain;

import homeposting.app.domain.entities.Account;
import homeposting.app.domain.entities.Subsystem;
import homeposting.app.domain.entities.TransactionKind;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class SubsystemWrapper {
	private final Subsystem subsystem;
	private final List<SelectItem> accountsItems;
	private final List<SelectItem> transactionsKindsItems;

	public SubsystemWrapper(Subsystem subsystem){
		this.subsystem =  subsystem;

		int index = 0;
		accountsItems = new ArrayList<SelectItem>();
		for(Account account : subsystem.getAccounts()){
			accountsItems.add(new SelectItem(index++,account.getName()));
		}
		
		index = 0;
		transactionsKindsItems = new ArrayList<SelectItem>();
		for(TransactionKind tk : subsystem.getTransactionKinds()){
			transactionsKindsItems.add(new SelectItem(index++,tk.getName()));
		}
		
	}

	public Subsystem getSubsystem() {
		return subsystem;
	}

	public List<SelectItem> getAccountsItems() {
		return accountsItems;
	}

	public List<SelectItem> getTransactionsKindsItems() {
		return transactionsKindsItems;
	}
	
}
