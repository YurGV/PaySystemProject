package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.Transactions;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.UserCards;
import by.grodno.pvt.site.webappsample.dto.CardDTO;
import by.grodno.pvt.site.webappsample.dto.TransactionsDTO;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import by.grodno.pvt.site.webappsample.service.CardService;

import by.grodno.pvt.site.webappsample.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CardController {

    @Autowired
    private ConversionService convertionService;
    @Autowired
    private CardService cardService;
    @Autowired
    private TransactionsService transService;


    @GetMapping("/cards")
    public String getAllCards(Model model) {

        List<CardDTO> cards = cardService.getCards().stream().map(u -> convertionService.convert(u, CardDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("cards", cards);
        return "cardsList";
    }

    @GetMapping("/card/{id}")
    public String ViewCardForm(@PathVariable Integer id, Model model) {
        model.addAttribute("userCards", cardService.getCard(id));
        return "cardData";
    }

    @GetMapping("/card/edit/{id}")
    public String editCardForm(@PathVariable Integer id, Model model) {
        model.addAttribute("userCards", cardService.getCard(id));
        return "cardDataEdit";
    }
    @PostMapping("/cards/edit/{id}")
    public String editCard(
            @PathVariable Integer id,
            @RequestParam(value="cardName") String cardName,
            @RequestParam(value="balance") Double balance,
            @RequestParam(value="valid") Integer valid,
            @RequestParam(value="lock") Boolean lock)
    {

        UserCards card = new UserCards();
        card.setId(id);
        card.setCardName(cardName);
        card.setBalance(balance);
        card.setValid(valid);
        card.setLock(lock);


        cardService.editCard(card);
        return "redirect:/cards";
    }

//    @PostMapping("/cards/edit/{id}")
//    public String editCard(@PathVariable Integer id, @Valid CardDTO cardDTO, BindingResult br, Model model) {
//
//        if (br.hasErrors()) {
//            model.addAttribute("cardDTO", cardDTO);
//            return "cardDataEdit";
//        }
//
//        UserCards userCards = new UserCards();
//        userCards.setId(id);
//        userCards.setCardName(cardDTO.getCardName());
//        userCards.setBalance(cardDTO.getBalance());
//        userCards.setValid(cardDTO.getValid());
//        userCards.setLock(cardDTO.getLock());
//
//        cardService.edit(cardDTO);
//
//        return "redirect:/cards";
//    }

    @GetMapping(value = "/cards/addCard")
    public String createCardForm(UserCards userCards, Model model) {
        userCards = new UserCards();
        model.addAttribute("UserCards", userCards);
        return "addCard";
        //cardRepo.save(new UserCards(null, "Visa", 220.0, 2025,true));
    }

    @PostMapping(value = "/cards/addCard")
	public String createCard(@RequestParam(value="cardName") String cardName,
							 @RequestParam(value="balance") Double balance,
							 @RequestParam(value="valid") Integer valid,
                             @RequestParam(value="lock") Boolean lock)
							  {

		UserCards card = new UserCards();

		card.setCardName(cardName);
		card.setBalance(balance);
		card.setValid(valid);
		card.setLock(lock);

		cardService.saveCard(card);
		return "redirect:/cards";
	}

    @GetMapping("/card/delete/{id}")
    public String deleteCard(@PathVariable Integer id) {
        cardService.deleteCard(id);
        return "redirect:/cards";
    }

    @GetMapping("/card/replenishment/{id}")                        //изменение одного филда для id с редактирование на странице
    public String addMoneyForm(@PathVariable Integer id, Model model) {
        model.addAttribute("userCards", cardService.getCard(id));
        return "replenishment";
    }

    @PostMapping("/card/replenishment/{id}")
    public String addMoney(@PathVariable Integer id,
                           @RequestParam(value="balance") Double balance) {
        cardService.updateBalancePlus(balance, id);

        Transactions trans = new Transactions();
        trans.setProcedure("receive");
        trans.setTransactionDate(new Date());
        trans.setValue(balance);
        transService.saveTrans(trans);
        return "redirect:/cards";
    }

    @GetMapping("/card/lock/{id}")                        //изменение на статус Lock
    public String editStatusLock(@PathVariable Integer id) {
        cardService.updateStatusCard(false, id);
        return "redirect:/cards";
    }

    @GetMapping("/card/unlock/{id}")                        //изменение на статус UnLock
    public String editStatusUnlock(@PathVariable Integer id) {
        cardService.updateStatusCard(true, id);
        return "redirect:/cards";
    }

    @GetMapping("/card/transfer/{id}")                        //изменение одного филда для id с редактирование на странице
    public String transferMoneyForm(@PathVariable Integer id, Model model) {
        model.addAttribute("userCards", cardService.getCard(id));
        return "transfer";
    }

    @PostMapping("/card/transfer/{id}")
    public String transferMoney(@PathVariable Integer id,
                           @RequestParam(value="balance") Double balance) {
        cardService.updateBalanceMinus(balance, id);
        return "redirect:/cards";
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {

        List<TransactionsDTO> trans = transService.getTrans().stream().map(u -> convertionService.convert(u, TransactionsDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("trans", trans);
        return "transactionsList";
    }




}
