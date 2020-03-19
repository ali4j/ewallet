package io.github.ali4j.ewallet.core.controller.wallet

import io.github.ali4j.ewallet.core.model.Wallet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WalletControllersIntegrationTest(
        @Autowired val restTemplate: TestRestTemplate) {



    @Test
    fun `Should Return a valid Wallet`() {
        val walletId = "708835db-5e8d-49df-b57a-60b912aca11b"

        val responseEntity:ResponseEntity<Wallet> =
                restTemplate.getForEntity("/core/wallet/$walletId", Wallet::class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
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

    @Test
    fun `Should Return a 200 with valid wallet creation values`() {
        val email = "test_email@test.com"
        val fullName = "test_full_name1"

        val requestBody = CreateNewWalletController.CreateNewWalletRequest(email, fullName)
        val responseEntity:ResponseEntity<String> =
                restTemplate.postForEntity("/core/wallet", requestBody, String::class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(UUID.fromString(responseEntity.body)).isNotNull()

    }

    @Test
    fun `Should Return a 500 with with en existing email address`() {
        val email = "test@test.com"
        val fullName = "test_full_name"

        val requestBody = CreateNewWalletController.CreateNewWalletRequest(email, fullName)
        val responseEntity:ResponseEntity<String> =
                restTemplate.postForEntity("/core/wallet", requestBody, String::class)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)

    }




}