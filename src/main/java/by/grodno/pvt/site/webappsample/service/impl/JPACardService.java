package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.UserCards;
import by.grodno.pvt.site.webappsample.dto.CardDTO;
import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;
import by.grodno.pvt.site.webappsample.repo.CardRepo;
import by.grodno.pvt.site.webappsample.service.CardService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
@Transactional
public class JPACardService implements CardService, InitializingBean {

    @Autowired
    private CardRepo cardRepo;

    @Override
    public List<UserCards> getCards() {
        return cardRepo.findAll();
    }
    @Override
    public UserCards getCard(Integer id) {
        return cardRepo.getOne(id);
    }
    public void addCard(List<UserCards> cards) {
        cardRepo.saveAll(cards);
    }
    @Override
    public void saveCard(UserCards cards) {
        cardRepo.save(cards);
    }
    @Override
    public void deleteCard(Integer number) {
        cardRepo.deleteById(number);
    }
    @Override
    public void edit(CardDTO cardDTO) {
        UserCards findById = cardRepo.findById(cardDTO.getId()).orElseThrow(() -> new UserNotFoundException());
        findById.setCardName(cardDTO.getCardName());
        findById.setBalance(cardDTO.getBalance());
        findById.setValid(cardDTO.getValid());
        findById.setLock(cardDTO.getLock());
        cardRepo.save(findById);
    }
    @Override
    public void afterPropertiesSet() throws Exception {

        cardRepo.save(new UserCards(null, "Visa", 220.0, 2025,true));
        cardRepo.save(new UserCards(null, "Maestro", 999.1, 2030,true));
        cardRepo.save(new UserCards(null, "BelCard", 10.5, 2022,false));
    }

    @Override
    public void updateBalancePlus(Double balance, Integer id) {
        cardRepo.updateBalancePlus(balance, id);
    }
}
