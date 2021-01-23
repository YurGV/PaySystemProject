package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserCards;
import by.grodno.pvt.site.webappsample.dto.CardDTO;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCardsDomainToDTOConverter implements Converter<UserCards, CardDTO> {

	@Override
	public CardDTO convert(UserCards source) {

		CardDTO u = new CardDTO();
		u.setId(source.getId());
		u.setCardName(source.getCardName());
		u.setBalance(source.getBalance());
		u.setValid(source.getValid());
		u.setLock(source.getLock());
		return u;
	}
}


