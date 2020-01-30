package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.model.Wallet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WalletControllersIntegrationTest(
        @Autowired val restTemplate: TestRestTemplate) {



    @Test
    fun `Should Return a valid Wallet`() {
        val walletId = "708835db-5e8d-49df-b57a-60b912aca11b"

        val responseEntity:ResponseEntity<Wallet> =
                restTemplate.getForEntity("/core/wallet/$walletId", Wallet::class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isNotEqualTo(null)
    }

    @Test
    fun `Should Return a 404 Error with none-existing Wallet id`() {
        val walletId = "00000000-0000-0000-0000-000000000000"

        val responseEntity:ResponseEntity<Wallet> =
                restTemplate.getForEntity("/core/wallet/$walletId", Wallet::class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `Should Return a 400 Error with invalid Wallet id`() {
        val walletId = "xxxx"

        val responseEntity:ResponseEntity<Wallet> =
                restTemplate.getForEntity("/core/wallet/$walletId", Wallet::class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }




}