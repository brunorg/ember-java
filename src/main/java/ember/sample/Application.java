package ember.sample;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jasminb.jsonapi.ResourceConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ember.sample.model.Customer;
import ember.sample.model.Order;
import ember.sample.model.OrderItem;
import ember.sample.model.Product;

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
        String allowedMethods[] = { "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH" };
        String emberServer = "http://localhost:4200";

        registry.addMapping("/customers").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/customers/{id}").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/products").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/products/{id}").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/orders").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/orders/{id}").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/order-items").allowedOrigins(emberServer).allowedMethods(allowedMethods);
        registry.addMapping("/order-items/{id}").allowedOrigins(emberServer).allowedMethods(allowedMethods);

      }
    };
  }

  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    return builder;
  }

  @Bean
  public ResourceConverter resourceConverter() {
    ObjectMapper objectMapper = objectMapperBuilder().createXmlMapper(false).build();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

    return new ResourceConverter(objectMapper, Customer.class, Product.class, Order.class, OrderItem.class);
  }

}
