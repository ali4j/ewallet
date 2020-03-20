package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.service.WalletService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AttachNewCardToWalletController(
        @Autowired val walletService: WalletService) {


    @PostMapping("/core/wallet/{walletId}/card")
    @ApiOperation("attaches a new card to an existing wallet")
    fun handle(
            @PathVariable walletId: UUID,
            @RequestBody request:AttachNewCardToWalletRequest) : String{

        return walletService.attachNewCardToWallet(
                walletService.getWallet(walletId),
                request.cardPan,
                request.cardName,
                request.cardExpirationDate).toString()
    }


    data class AttachNewCardToWalletRequest(
            val cardPan:String,
            val cardExpirationDate:String,
            val cardName:String
    )

}