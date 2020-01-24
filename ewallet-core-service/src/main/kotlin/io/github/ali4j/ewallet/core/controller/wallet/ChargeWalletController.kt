package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.service.WalletService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ChargeWalletController(@Autowired val walletService:WalletService) {

    @PostMapping("/core/wallet/{id}/charge")
    @ApiOperation("charge wallet balance")
    fun chargeWallet(
            @PathVariable id : UUID,
            @RequestBody request : ChargeWalletRequest){
        walletService.chargeWallet(id, request.amount)
    }


    data class ChargeWalletRequest(val amount:Long)

}