package br.edu.ifsp.estagiei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@EnableWebMvc
@SpringBootApplication
@ServletComponentScan
@OpenAPIDefinition(
		info = @Info(
				title = "Estagiei API", 
				version = "0.7", 
				description = "Documentação do sistema de vagas de estágio - Estagiei",
				license = @License(
							name = "MIT License",
							url = "https://github.com/EquipeWeCode/estagiei-backend/blob/develop/LICENSE"
						)
				)
		)
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins(
						"http://localhost:3000", 
						"https://estagiei.netlify.app",
						"https://estagiei.vercel.app")
				.allowedHeaders("*")
				.allowedMethods("*");
			}
		};
	}
	
}
