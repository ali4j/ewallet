package io.github.ali4j.ewallet.core.controller.card

import io.github.ali4j.ewallet.core.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UpdateCardController(@Autowired val cardService: CardService) {

    @PutMapping("/core/card/{id}")
    fun handle(
            @PathVariable id : UUID,
            @RequestBody request : UpdateCardRequest) {

        cardService.updateCard(id, request.pan, request.name, request.expirationDate)

    }

    data class UpdateCardRequest(
            val pan : String,
            val name : String,
            val expirationDate : String
    )
}