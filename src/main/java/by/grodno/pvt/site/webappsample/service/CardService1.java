package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.UserCards;
import by.grodno.pvt.site.webappsample.dto.CardDTO;

import java.util.List;

public interface CardService1 {

	List<UserCards> getCards();

	UserCards getCard(Integer id);

	void addCard(List<UserCards> cards);

	void saveCard(UserCards cards);

	void deleteCard(Integer number);

	/**
	 * Updates FirstName and LastName only.
	 * 
	 * @param cardDTO
	 */
	void edit(CardDTO cardDTO);
}
