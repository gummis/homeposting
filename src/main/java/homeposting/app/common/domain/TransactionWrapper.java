package homeposting.app.common.domain;

import homeposting.app.common.data.util.Pair;
import homeposting.app.common.jsf.CashConverter;
import homeposting.app.domain.entities.AccountFlow;
import homeposting.app.domain.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionWrapper {
	
	private final Transaction transaction;
	private final TransactionViewType type;
	private final List<Pair<String,String>> lines;

	public TransactionWrapper(Transaction transaction){
		this.transaction = transaction;
		lines = new ArrayList<Pair<String,String>>();
		type = recognizeType(transaction,lines);
	}

	private static TransactionViewType recognizeType(Transaction t,List<Pair<String,String>> lines) {
		List<AccountFlow> flows = t.getFlows();
		AccountFlow accFlow1 = flows.get(0);
		int flow1 = accFlow1.getFlow();
		boolean plus1 = flow1 > 0;
		if(flows.size() == 1){
			if(plus1){
				lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),"+" + CashConverter.fromInteger(flow1)));
				return TransactionViewType.PROFIT;
			}else{
				lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),CashConverter.fromInteger(flow1)));
				return TransactionViewType.COST;
			}
		}
		if(flows.size() == 2){
			int flow2 = flows.get(1).getFlow();
			if( (plus1) != (flow2 > 0) ){
				int sum = flow1 + flow2;
				if(sum == 0){
					lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),"+" + CashConverter.fromInteger(flow1)));
					return TransactionViewType.TRANSFER;
				}else if(sum < 0){
					lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),"+" + CashConverter.fromInteger(flow1)));
					return TransactionViewType.TRANSFER_COST;
				}
			}
		}
		for(AccountFlow af : flows){
			if(plus1 != (af.getFlow() > 0)){
				lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),"+" + CashConverter.fromInteger(flow1)));
				return TransactionViewType.OTHER;
			}
		}
		if(plus1){
			lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),"+" + CashConverter.fromInteger(flow1)));
			return TransactionViewType.MULTIPROFIT;
		}else{
			lines.add(new Pair<String,String>(accFlow1.getAccount().getName(),"+" + CashConverter.fromInteger(flow1)));
			return TransactionViewType.MULTICOST;
		}
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public TransactionViewType getType() {
		return type;
	}

	public List<Pair<String, String>> getLines() {
		return lines;
	}
	
	
	
	
}
