package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.UserCards;
import by.grodno.pvt.site.webappsample.dto.CardDTO;

import java.util.List;

public interface CardService {

    List<UserCards> getCards();

    UserCards getCard(Integer id);

    void addCard(List<UserCards> cards);

    void saveCard(UserCards cards);

    void deleteCard(Integer number);

    void edit(CardDTO cardDTO);

    void updateBalancePlus(Double balance, Integer id);

    void updateBalanceMinus(Double balance, Integer id);

    void updateStatusCard(boolean lock, Integer id);

    void editCard(UserCards card);


}
