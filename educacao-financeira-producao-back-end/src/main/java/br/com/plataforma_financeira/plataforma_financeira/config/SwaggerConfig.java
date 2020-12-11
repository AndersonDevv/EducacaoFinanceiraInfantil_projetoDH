package br.com.plataforma_financeira.plataforma_financeira.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.plataforma_financeira.plataforma_financeira.service.EmailService;
import br.com.plataforma_financeira.plataforma_financeira.service.MockEmailService;
import br.com.plataforma_financeira.plataforma_financeira.service.SmtpEmailService;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration ////indica que é uma classe de configuração
@EnableSwagger2// Habilita o Swagger
public class SwaggerConfig {
	/*
	private static final String CONTACT_EMAIL = "plataforma_fincancrira_infantil@pefi.com.br";
	private static final String CONTACT_BITBUCKET = "https://bitbucket.org/luciano_dialog/educacao-financeira-producao/src/master/";
	private static final String CONTACT_NAME = "https://bitbucket.org/luciano_dialog/educacao-financeira-producao/src/master/";
	*/
	
	private static final String API_DESCRIPTION = "Projeto Inegtaror - Curso Java Full Stack";
	private static final String BASE_PACKAGE = "br.com.plataforma_financeira.plataforma_financeira";
	private static final String API_TITLE = "Plataforma para Educação Financeira Infantil";
	private static final String API_VERSION = "1.0.0";
	
	@Bean//indica que é gerenciado pelo próprio spring
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
          .paths(PathSelectors.any())
          .build().apiInfo(buildApInfo());
    }

	   private ApiInfo buildApInfo() {

	        return new ApiInfoBuilder()
	                .title(API_TITLE)
	                .description(API_DESCRIPTION)
	                .version(API_VERSION)
	                //.contact(new Contact(CONTACT_NAME, CONTACT_BITBUCKET, CONTACT_EMAIL))
	                .build();
	    
	   }
	  
	   /*
	   @Bean
		public EmailService emailService() {
			return new MockEmailService();
		}
		
		*/
	  
	   
	   @Bean
		public EmailService emailService() {
			return new SmtpEmailService();
		}
		
		
}
