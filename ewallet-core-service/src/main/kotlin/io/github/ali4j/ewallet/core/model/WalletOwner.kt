package io.github.ali4j.ewallet.core.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*


@Entity
@Getter @Setter
class WalletOwner{

    constructor() {}

    constructor(email: String?, fullName: String?) {
        this.email= email
        this.fullName = fullName
    }

    @Id
    var email : String? = null
    var fullName : String? = null
    @OneToOne
    @JoinColumn(name = "wallet_id")
    var wallet : Wallet? = null

}

