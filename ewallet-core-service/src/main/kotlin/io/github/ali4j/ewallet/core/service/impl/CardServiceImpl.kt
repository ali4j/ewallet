package io.github.ali4j.ewallet.core.service.impl

import io.github.ali4j.ewallet.core.common.exception.CardNotFoundException
import io.github.ali4j.ewallet.core.common.exception.CardWithPanExistsException
import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.repository.CardRepository
import io.github.ali4j.ewallet.core.service.CardService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardServiceImpl(@Autowired val cardRepository: CardRepository) : CardService {

    val logger : Logger = LoggerFactory.getLogger(CardServiceImpl::class.qualifiedName)

    override fun getCard(pan: String): Card? {
        return cardRepository.findByPan(pan).orElse(null)
    }

    override fun addCard(pan: String, name: String, expirationDate: String) : Card{
        val card = getCard(pan)
        if(card!=null)
            throw CardWithPanExistsException()
        else  {
            val newCard = Card(pan, name, expirationDate)
            cardRepository.save(newCard)
            logger.info("new card with pan:{} and id:{} is saved.", newCard.pan, newCard.id)
            return newCard
        }


    }

    override fun getCard(id: UUID) : Card {

        val maybeCard = cardRepository.findById(id)
        if (maybeCard.isPresent) {
            val card = maybeCard.get()
            logger.info("card with pan:{} and id:{} is returned.", card.pan, card.id)
            return card
        } else
            throw CardNotFoundException()

    }

    override fun chargeCard(id: UUID, amount: Long) {
        TODO("not implemented")
    }
}