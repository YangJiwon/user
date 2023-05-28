package com.project.ably.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;
import com.project.ably.model.response.FolderResponse;
import com.project.ably.model.response.LoginResponse;
import com.project.ably.model.response.MemberResponse;
import com.project.ably.model.response.ProductResponse;
import com.project.ably.model.response.WishResponse;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api(TypeResolver typeResolver){
		return new Docket(DocumentationType.OAS_30)
				.additionalModels(
						typeResolver.resolve(Void.class),
						typeResolver.resolve(MemberResponse.class),
						typeResolver.resolve(LoginResponse.class),
						typeResolver.resolve(FolderResponse.class),
						typeResolver.resolve(WishResponse.class),
						typeResolver.resolve(ProductResponse.class)
				)
				.useDefaultResponseMessages(true)
				.apiInfo(apiInfo())
				.securityContexts(List.of(securityContext()))
				.securitySchemes(List.of(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.project.ably"))
				.paths(PathSelectors.any())
				.build();
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("에이블리 사전과제")
				.description("백엔드 엔지니어 사전 과제")
				.version("1.0")
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];

		authorizationScopes[0] = authorizationScope;
		return List.of(new SecurityReference("JWT", authorizationScopes));
	}
}
