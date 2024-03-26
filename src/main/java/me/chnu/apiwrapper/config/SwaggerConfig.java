package me.chnu.apiwrapper.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

@Configuration
public class SwaggerConfig {
    static {
        SpringDocUtils.getConfig()
                .replaceWithClass(Pageable.class, org.springdoc.core.converters.models.Pageable.class);
    }

    @Bean
    public GroupedOpenApi publicAPI() {
        final Info info = new Info()
                .title("API Wrapper Example API")
                .version("v1")
                .description("API Wrapper에 대한 예시");

        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan("me.chnu.apiwrapper.presentation.api")
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(info);
                })
                .build();
    }
}
