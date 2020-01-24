package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.service.WalletService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AttachNewCardToWalletController(
        @Autowired val walletService: WalletService) {


    @PostMapping("/core/wallet/{w_id}/card")
    @ApiOperation("attaches a new card to an existing wallet")
    fun handle(
            @PathVariable walletId: UUID,
            @RequestBody request:AttachNewCardToWalletRequest){

        walletService.attachNewCardToWallet(
                walletService.getWallet(walletId),
                request.cardPan,
                request.cardName,
                request.cardExpirationDate)
    }


    data class AttachNewCardToWalletRequest(
            val cardPan:String,
            val cardExpirationDate:String,
            val cardName:String
    )

}