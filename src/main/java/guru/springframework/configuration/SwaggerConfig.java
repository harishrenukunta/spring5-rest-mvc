package guru.springframework.configuration;

import com.sun.deploy.ui.AppInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(getApiInfo());

    }

    public ApiInfo getApiInfo(){
        Contact contact = new Contact("Harish", "http://www.harish.com", "harish_r9@yahoo.co.uk");

        return new ApiInfo("Harish Vendors Repository",
                "Vendor Management System",
                "1.0", "http://www.linkedIn.com",contact, "1.3.0",
                "http://www.license.com",new ArrayList<>());

    }
}
