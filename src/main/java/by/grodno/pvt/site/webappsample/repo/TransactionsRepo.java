package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {

//    SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
//    FROM Orders
//    INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;
}
