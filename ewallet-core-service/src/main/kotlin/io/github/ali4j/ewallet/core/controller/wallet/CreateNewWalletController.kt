package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.service.WalletService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateNewWalletController(
        @Autowired val walletService: WalletService
) {


    @PostMapping("/core/wallet")
    @ApiOperation("creates an instance of wallet and wallet_owner and associates it with the wallet_owner")
    fun handle(
            @RequestBody request:CreateNewWalletRequest
    ) : String {

        val walletId = walletService.createNewWallet(request.email, request.fullName)
        return walletId.toString()

    }


    data class CreateNewWalletRequest (
            val email : String,
            val fullName : String
    )




}
