package homeposting.app.domain;

public enum TransactionType {
	/** Kosztowa, np. zakupy, opłaty, zgubione, manko ujemne itd. */
	COST,

	/** Przychodowa np. wypłata, zarobione, znalezione, manko dodatnie */
	INCOMING,
	
	/** Transfer pomiędzy kontami np. wypłata lub wpłata z/do bankomatu, oddanie lub odbranie długu , przelew bankowy*/
	TRANSFER,

	/** Transfer pomiędzy kontami z kosztem usługi np. wypłata lub wpłata z/do bankomatu, oddanie lub odbranie długu , przelew bankowy*/
	TRANSFER_AND_COST;
}
