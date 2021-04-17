package br.com.cvc.banktransferscheduler.gateway.config;

import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final TypeResolver typeResolver;

    @Autowired
    public SwaggerConfiguration(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cvc.banktransferscheduler"))
                .build()
                .apiInfo(getApiInfo())
                .host("localhost:8080")
                .additionalModels(typeResolver.resolve(Fee.class));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Bank Transfers Scheduler")
                .description("This is a sample API documentation by Victor Xavier for a Bank Transfers Scheduler API")
                .contact(new Contact("Victor Xavier de Melo",
                        "https://github.com/viictor1224/bank-transfer-scheduler",
                        "victorxm1@gmail.com"))
                .version("1.0.0")
                .build();
    }

}
