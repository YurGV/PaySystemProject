package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.Transactions;


import java.util.List;

public interface TransactionsService {

	List<Transactions> getTrans();

	void saveTrans(Transactions transactions);

}
