package io.github.ali4j.ewallet.core.service.impl

import io.github.ali4j.ewallet.core.common.exception.CardNotFoundException
import io.github.ali4j.ewallet.core.common.exception.CardWithPanExistsException
import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.repository.CardRepository
import io.github.ali4j.ewallet.core.service.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardServiceImpl(@Autowired val cardRepository: CardRepository) : CardService {

    override fun getCard(pan: String): Card {
        return cardRepository.findByPan(pan).get()
    }

    override fun addCard(pan: String, name: String, expirationDate: String) : Card{
        val card = getCard(pan)
        if(card!=null)
            throw CardWithPanExistsException()
        else  {
            var newCard = Card(pan, name, expirationDate)
            cardRepository.save(newCard)
            return newCard
        }


    }

    override fun getCard(id: UUID) : Card {

        var maybeCard = cardRepository.findById(id)
        if (maybeCard.isPresent)
            return maybeCard.get()
        else
            throw CardNotFoundException()

    }

    override fun chargeCard(id: UUID, amount: Long) {
        TODO("not implemented")
    }
}