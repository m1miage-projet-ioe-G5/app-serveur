package m1_miage.tlse.ioe.G5.moovly.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration extends org.springframework.web.cors.CorsConfiguration implements WebMvcConfigurer {

    /**
     * Help with configuring {@link HandlerMapping} path matching options such as
     * whether to use parsed {@code PathPatterns} or String pattern matching
     * with {@code PathMatcher}, whether to match trailing slashes, and more.
     * @param configurer
     * @see PathMatchConfigurer
     * @since 4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Appliquer à tous les endpoints
                .allowedOrigins("http://localhost:4200")  // URL de votre app Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);  // Cache pendant 1 heure
    }
}