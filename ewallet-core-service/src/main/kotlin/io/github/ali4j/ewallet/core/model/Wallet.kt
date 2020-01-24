package io.github.ali4j.ewallet.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Getter @Setter
class Wallet {

    enum class WalletState {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    var id : UUID? = null

    var balance : Long = 0

    @Column(name ="blocked_balance")
    var blockedBalance : Long = 0

    @Enumerated(EnumType.STRING)
    var state : WalletState? = WalletState.ACTIVE

}

