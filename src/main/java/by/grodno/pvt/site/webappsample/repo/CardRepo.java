package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.UserCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepo extends JpaRepository<UserCards, Integer> {

    CardRepo findByCardName(String cardName);


    @Modifying
    @Query("UPDATE UserCards c SET c.balance = :balance + balance WHERE c.id = :id")
    void updateBalancePlus(@Param("balance") Double balance, @Param("id") Integer id);

    @Modifying
    @Query("UPDATE UserCards c SET c.balance = balance - :balance WHERE c.id = :id")
    void updateBalanceMinus(@Param("balance") Double balance, @Param("id") Integer id);

    @Modifying
    @Query("UPDATE UserCards c SET c.lock = :lock WHERE c.id = :id")
    void updateStatusCard(@Param("lock") Boolean lock, @Param("id") Integer id);


}
