package io.github.ali4j.ewallet.core.service.impl

import io.github.ali4j.ewallet.core.common.exception.WalletNotFoundException
import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.model.Wallet
import io.github.ali4j.ewallet.core.repository.CardRepository
import io.github.ali4j.ewallet.core.repository.WalletRepository
import io.github.ali4j.ewallet.core.service.CardService
import io.github.ali4j.ewallet.core.service.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class WalletServiceImpl
    (
            @Autowired val walletRepository:WalletRepository,
            @Autowired val cardService: CardService,
            @Autowired val cardRepository: CardRepository) : WalletService {

    override fun attachNewCardToWallet(wallet: Wallet, pan: String, name: String, expirationDate: String) {

        var card = cardService.addCard(pan, name, expirationDate)
        card.wallet = wallet
        cardRepository.save(card)
    }

    @Transactional
    override fun chargeWallet(id: UUID, amount: Long) {

        var wallet = getWallet(id)

        var newBalance = wallet.balance + amount
            wallet.balance = newBalance
            walletRepository.save(wallet)
    }

    override fun getWallet(id: UUID): Wallet {
        var maybeWallet = walletRepository.findById(id)
        if(maybeWallet.isPresent){
            return maybeWallet.get()
        } else
            throw WalletNotFoundException()
    }

    @Transactional
    override fun transferWalletToCard(id: UUID, pan: String) {
        TODO("not implemented")
    }

    override fun getCards(id: UUID): List<Card> {
        var wallet : Wallet = getWallet(id)

        return cardRepository.findAllByWallet(wallet)
    }



}