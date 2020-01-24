package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.service.WalletService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GetWalletCardsController(@Autowired val walletService: WalletService) {


    @GetMapping("/core/wallet/{id}/cards")
    @ApiOperation("returns a list of cards of a wallet")
    fun handle(@PathVariable id: UUID) : List<Card> {
        return walletService.getCards(id)
    }

}