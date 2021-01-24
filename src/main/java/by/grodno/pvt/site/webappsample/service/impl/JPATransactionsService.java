package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Transactions;


import by.grodno.pvt.site.webappsample.repo.TransactionsRepo;
import by.grodno.pvt.site.webappsample.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JPATransactionsService implements TransactionsService {

    @Autowired
    TransactionsRepo transRepo;

    @Override
    public List<Transactions> getTrans() {
        return transRepo.findAll();
    }

    @Override
    public void saveTrans(Transactions transaction) {
        transRepo.save(transaction);
    }

}
