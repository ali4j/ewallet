package io.github.ali4j.ewallet.core.repository

import io.github.ali4j.ewallet.core.model.Wallet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface  WalletRepository : CrudRepository<Wallet, UUID>