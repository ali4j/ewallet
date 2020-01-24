package io.github.ali4j.ewallet.core.service

import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.model.Wallet
import java.util.*

interface WalletService {

    fun chargeWallet(id: UUID, amount:Long)

    fun getWallet(id: UUID) : Wallet

    fun transferWalletToCard(id:UUID, pan:String)

    fun getCards(id:UUID) : List<Card>

    fun attachNewCardToWallet(wallet:Wallet, pan:String, name:String, expirationDate:String)

}


