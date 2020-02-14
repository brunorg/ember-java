package ember.sample;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jasminb.jsonapi.ResourceConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
import ember.sample.repository.CustomerRepository;
import ember.sample.repository.OrderItemRepository;
import ember.sample.repository.OrderRepository;
import ember.sample.repository.ProductRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

 @Autowired
  private CustomerRepository repository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    repository.deleteAll();
    productRepository.deleteAll();
    orderItemRepository.deleteAll();
    orderRepository.deleteAll();

    // save a couple of customers
    Customer john = new Customer();
    john.setFirstName("John");
    john.setLastName("Doe");

    Customer frodo = new Customer();
    frodo.setFirstName("Frodo");
    frodo.setLastName("Baggins");

    repository.save(john);
    repository.save(frodo);

    // save a couple od products
    Product sapiens = new Product();
    sapiens.setName("Sapiens");
    sapiens.setDescription("Sapiens: A Brief History of Humankind");
    sapiens.setPrice(new BigDecimal(26.84d, MathContext.DECIMAL64));

    Product prideAndPrejudice = new Product();
    prideAndPrejudice.setName("Pride and Prejudice");
    prideAndPrejudice.setDescription("Vanity, not love, has been my folly");
    prideAndPrejudice.setPrice(new BigDecimal(25.81d, MathContext.DECIMAL64));

    Product bigBrother = new Product();
    bigBrother.setName("1984");
    bigBrother.setDescription("Nineteen Eighty-Four");
    bigBrother.setPrice(new BigDecimal(29.90d, MathContext.DECIMAL64));

    productRepository.save(sapiens);
    productRepository.save(prideAndPrejudice);
    productRepository.save(bigBrother);

    // fetch all customers
    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");
    for (Customer customer : repository.findAll()) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer
    System.out.println("Customer found with findByFirstName('John'):");
    System.out.println("--------------------------------");
    System.out.println(repository.findByFirstName("John"));

    System.out.println("Customers found with findByLastName('Frodo'):");
    System.out.println("--------------------------------");
    for (Customer customer : repository.findByLastName("Frodo")) {
      System.out.println(customer);
    }

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
