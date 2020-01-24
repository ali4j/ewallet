package io.github.ali4j.ewallet.core.controller.card

import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GetCardController(@Autowired val cardService: CardService) {

    @GetMapping("/core/card/{id}")
    fun handle(@PathVariable id : UUID) : Card {
        return cardService.getCard(id)
    }
}