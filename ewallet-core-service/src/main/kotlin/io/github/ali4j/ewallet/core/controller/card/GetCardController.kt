package io.github.ali4j.ewallet.core.controller.card

import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetCardController(@Autowired val cardService: CardService) {

    @GetMapping("/core/card/{pan}")
    fun handle(@PathVariable pan : String) : Card {
        return cardService.getCard(pan)
    }
}