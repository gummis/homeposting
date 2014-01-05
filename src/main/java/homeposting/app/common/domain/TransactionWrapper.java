package homeposting.app.common.domain;

import homeposting.app.common.jsf.CashConverter;
import homeposting.app.domain.entities.AccountFlow;
import homeposting.app.domain.entities.Transaction;

import java.util.List;

public class TransactionWrapper {
	
	private final Transaction transaction;
	private final TransactionViewType type;
	
	private String accountsInfo;
	private String cashInfo;
	

	public TransactionWrapper(Transaction transaction){
		this.transaction = transaction;
		type = ini(transaction);
	}

	private TransactionViewType ini(Transaction t) {

		List<AccountFlow> flows = t.getFlows();
		AccountFlow accFlow1 = flows.get(0);
		int flow1 = accFlow1.getFlow();
		boolean plus1 = flow1 > 0;
		if(flows.size() == 1){
			accountsInfo = accFlow1.getAccount().getName();
			if(plus1){
				cashInfo = "+" + CashConverter.fromInteger(flow1);
				return TransactionViewType.PROFIT;
			}else{
				cashInfo = CashConverter.fromInteger(flow1);
				return TransactionViewType.COST;
			}
		}
		if(flows.size() == 2){
			AccountFlow accFlow2 = flows.get(1);
			int flow2 = accFlow2.getFlow();
			if( (plus1) != (flow2 > 0) ){
				int sum = flow1 + flow2;
				if(plus1){
					cashInfo = CashConverter.fromInteger(flow1);
					accountsInfo = accFlow2.getAccount().getName() + " => " + accFlow1.getAccount().getName();
				}else{
					cashInfo = CashConverter.fromInteger(flow2);
					accountsInfo = accFlow1.getAccount().getName() + " => " + accFlow2.getAccount().getName();
				}
				if(sum == 0){
					return TransactionViewType.TRANSFER;
				}else if(sum < 0){
					cashInfo += "(" + CashConverter.fromInteger(-sum) +  ")";
					return TransactionViewType.TRANSFER_COST;
				}
			}
		}
		int sum = transaction.getSum();
		String sumString = (sum> 0 ? "+" :"") + CashConverter.fromInteger(sum);
		accountsInfo = "Wiele...";
		//accountsInfoImage = "multi.jpg";
		for(AccountFlow af : flows){
			if(plus1 != (af.getFlow() > 0)){
				cashInfo = "Suma: " + sumString;
				return TransactionViewType.OTHER;
			}
		}
		cashInfo = sumString;
		if(flows.size() == 2){
			accountsInfo = accFlow1.getAccount().getName() + " / " +flows.get(1).getAccount().getName();
		}
		if(plus1){
			return TransactionViewType.MULTIPROFIT;
		}else{
			return TransactionViewType.MULTICOST;
		}

	}

	public Transaction getTransaction() {
		return transaction;
	}

	public TransactionViewType getType() {
		return type;
	}

	public String getAccountsInfo() {
		return accountsInfo;
	}
	public String getCashInfo() {
		return cashInfo;
	}

	
	
}
