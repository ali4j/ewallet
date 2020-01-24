package io.github.ali4j.ewallet.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Builder
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Getter @Setter @Builder
class Card {

    constructor() {}

    constructor(pan: String?, name: String?, expirationDate: String?) {
        this.pan = pan
        this.name = name
        this.expirationDate = expirationDate
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    var id:UUID? = null

    var pan:String? = null
    var name:String? = null
    @Column(name = "expiration_date")
    var expirationDate:String? = null
    var balance:Long? = 0

    @JsonIgnore
    @ManyToOne(targetEntity = Wallet::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    var wallet:Wallet? = null



}