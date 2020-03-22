package io.github.ali4j.ewallet.core.controller.card

import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GetAllCardsController(@Autowired val cardService : CardService) {

    @GetMapping("/core/card")
    fun handle(
            @RequestParam(defaultValue = "0") pageIndex : Int,
            @RequestParam(defaultValue = "2") pageSize: Int
    ) : List<Card>{

        return cardService.getCards(pageIndex, pageSize)

    }
}


