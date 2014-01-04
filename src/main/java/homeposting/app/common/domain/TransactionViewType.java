package homeposting.app.common.domain;

public enum TransactionViewType {
	COST("Koszt","kindcol_cost",true),
	PROFIT("Zysk","kindcol_profit",true),
	TRANSFER("Transfer","kindcol_transfer",true),
	TRANSFER_COST("Transfer+Koszt","kindcol_transfer_cost",true),
	MULTICOST("Koszt multi","kindcol_multicost",false),
	MULTIPROFIT("Zysk multi","kindcol_multiprofit",false),
	OTHER("Inny","kindcol_other",false)
	;
	
	private final String presentationName;
	private final String colorClass;
	private final boolean oneLine;

	private TransactionViewType(String presentationName, String colorClass, boolean oneLine) {
		this.presentationName = presentationName;
		this.colorClass = colorClass;
		this.oneLine = oneLine;
	}

	public String getPresentationName() {
		return presentationName;
	}

	public String getColorClass() {
		return colorClass;
	}
}
