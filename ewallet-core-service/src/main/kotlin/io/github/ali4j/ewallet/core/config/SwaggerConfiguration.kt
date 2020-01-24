package io.github.ali4j.ewallet.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
}

//    fun apiInfo():ApiInfo {
//        return ApiInfoBuilder()
//                .title("ewallet-core api")
//                .description("exposed api of core service")
//                .build()
//    }
//
//    fun createDocket(group:String, path:String):Docket {
//        return Docket(DocumentationType.SWAGGER_2)
//                .groupName(group)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex(path))
//                .build()
//
//    }
//
//    @Bean
//    open fun walletApi() : Docket {
//        return createDocket("Wallet", "/wallet.*")
//    }
//
//    @Bean
//    open fun cardApi() : Docket {
//        return createDocket("Card", "/card.*")
//    }



