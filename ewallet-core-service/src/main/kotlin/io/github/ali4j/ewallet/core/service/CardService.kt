package io.github.ali4j.ewallet.core.service

import io.github.ali4j.ewallet.core.model.Card
import java.util.*

interface CardService {
    fun getCard(id: UUID) : Card
    fun getCard(pan: String) : Card?
    fun chargeCard(id:UUID, amount:Long)
    fun addCard(pan:String,name:String,expirationDate:String) : Card
}

