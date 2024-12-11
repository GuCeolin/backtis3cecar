package tis3.tis3Back.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // Mapeia para todas as rotas
                .allowedOrigins("*")    // Permite todas as origens
                .allowedMethods("*")    // Permite todos os métodos (GET, POST, PUT, DELETE, etc)
                .allowedHeaders("*");   // Permite todos os cabeçalhos
    }
}

