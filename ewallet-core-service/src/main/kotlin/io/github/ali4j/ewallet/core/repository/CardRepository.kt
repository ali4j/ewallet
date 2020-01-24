package io.github.ali4j.ewallet.core.repository

import io.github.ali4j.ewallet.core.model.Card
import io.github.ali4j.ewallet.core.model.Wallet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CardRepository : CrudRepository<Card, UUID> {


    fun findAllByWallet(wallet: Wallet) : List<Card>
    fun findByPan(pan: String) : Optional<Card>

}