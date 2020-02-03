package ember.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer () {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/customers").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
        registry.addMapping("/customers/{id}").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
        registry.addMapping("/products").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
        registry.addMapping("/products/{id}").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
      }
    };
  }

}
