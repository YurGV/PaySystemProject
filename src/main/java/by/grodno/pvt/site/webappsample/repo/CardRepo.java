package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.UserCards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<UserCards, Integer> {

    CardRepo findByCardName(String cardName);


}
