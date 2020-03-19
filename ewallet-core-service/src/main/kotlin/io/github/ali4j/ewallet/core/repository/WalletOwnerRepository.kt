package io.github.ali4j.ewallet.core.repository

import io.github.ali4j.ewallet.core.model.WalletOwner
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WalletOwnerRepository : CrudRepository<WalletOwner, String> {
    fun findWalletOwnerByEmail(email:String) : Optional<WalletOwner>
}