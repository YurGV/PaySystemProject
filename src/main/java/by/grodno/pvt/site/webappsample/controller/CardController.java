package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.dto.CardDTO;
import by.grodno.pvt.site.webappsample.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CardController {

    @Autowired
    private ConversionService convertionService;
    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public String getAllCards(Model model) {

        List<CardDTO> cards = cardService.getCards().stream().map(u -> convertionService.convert(u, CardDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("cards", cards);
        return "cardsList";
    }


    @GetMapping("/card/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        model.addAttribute("userCards", cardService.getCard(id));

        return "cardData";
    }

//    @GetMapping("/card")
//    public String editUserForm( Model model) {
//
//        model.addAttribute("card", model);
//
//        return "cardData";
//    }





}
