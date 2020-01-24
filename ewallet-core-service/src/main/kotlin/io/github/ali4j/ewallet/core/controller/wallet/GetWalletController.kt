package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.model.Wallet
import io.github.ali4j.ewallet.core.service.WalletService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GetWalletController(@Autowired val walletService: WalletService) {


    @GetMapping("/core/wallet/{id}")
    @ApiOperation("returns a wallet")
    fun handle(@PathVariable id:UUID) : Wallet {
        return walletService.getWallet(id)
    }

}