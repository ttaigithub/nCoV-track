package fun.guan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @program: cst-sdk-service
 * @description: swagger配置
 * @author: JingXi
 * @create: 2019-08-14 14:05
 * @Copyright © 2019 JingXi. All rights reserved
 **/
@Configuration
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("fun.guan"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("远端")
                .description("弱隐私计算环境 API 网关接口")
                .termsOfServiceUrl("http://localhost:8081/dc")
                .version("1.0.0")
                .build();
    }
}
