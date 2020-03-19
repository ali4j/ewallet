package io.github.ali4j.ewallet.core.service.impl

import io.github.ali4j.ewallet.core.common.exception.EmailAddressIsAlreadyUsedException
import io.github.ali4j.ewallet.core.common.exception.WalletNotFoundException
import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.model.Wallet
import io.github.ali4j.ewallet.core.model.WalletOwner
import io.github.ali4j.ewallet.core.repository.CardRepository
import io.github.ali4j.ewallet.core.repository.WalletOwnerRepository
import io.github.ali4j.ewallet.core.repository.WalletRepository
import io.github.ali4j.ewallet.core.service.CardService
import io.github.ali4j.ewallet.core.service.WalletService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class WalletServiceImpl
    (
            @Autowired val walletRepository:WalletRepository,
            @Autowired val cardService: CardService,
            @Autowired val cardRepository: CardRepository,
            @Autowired val walletOwnerRepository: WalletOwnerRepository) : WalletService {



    @Transactional
    override fun createNewWallet(email:String, fullName:String) : UUID? {

        val maybeWalletOwner = walletOwnerRepository.findWalletOwnerByEmail(email)
        if (maybeWalletOwner.isPresent)
            throw EmailAddressIsAlreadyUsedException()
        else {
            val walletOwner = WalletOwner(email, fullName)
            val wallet = Wallet()
            wallet.balance = 0
            wallet.blockedBalance = 0

            walletRepository.save(wallet)

            walletOwner.wallet = wallet
            walletOwnerRepository.save(walletOwner)

            logger.info(
                    "new wallet and new wallet owner is create, wallet_id:{}, wallet_owner_email:{}",
                    wallet.id,
                    walletOwner.email
            )

            return wallet.id

        }

    }

    val logger :Logger = LoggerFactory.getLogger(WalletServiceImpl::class.qualifiedName)

    override fun attachNewCardToWallet(wallet: Wallet, pan: String, name: String, expirationDate: String) {

        val card = cardService.addCard(pan, name, expirationDate)
        card.wallet = wallet
        cardRepository.save(card)

        logger.info("card with pan:{} and id:{} is attached to wallet with id:{}",
                pan, card.id.toString(), wallet.id.toString())
    }

    @Transactional
    override fun chargeWallet(id: UUID, amount: Long) {

        val wallet = getWallet(id)

        val newBalance = wallet.balance + amount
            wallet.balance = newBalance
            walletRepository.save(wallet)

        logger.info("wallet with id:{} got charged for amount:{}", id.toString(), amount.toString())
    }

    override fun getWallet(id: UUID): Wallet {
        val maybeWallet = walletRepository.findById(id)
        if(maybeWallet.isPresent){
            val wallet = maybeWallet.get()
            logger.info("wallet with id:{} is returned", wallet.id)
            return wallet
        } else
            throw WalletNotFoundException()
    }

    @Transactional
    override fun transferWalletToCard(id: UUID, pan: String) {
        TODO("not implemented")
    }

    override fun getCards(id: UUID): List<Card> {
        val wallet : Wallet = getWallet(id)
        return cardRepository.findAllByWallet(wallet)
    }



}