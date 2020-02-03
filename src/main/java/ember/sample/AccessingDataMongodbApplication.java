package ember.sample;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ember.sample.model.Customer;
import ember.sample.model.Product;
import ember.sample.repository.CustomerRepository;
import ember.sample.repository.ProductRepository;

@SpringBootApplication
public class AccessingDataMongodbApplication implements CommandLineRunner {

  @Autowired
  private CustomerRepository repository;

  @Autowired
  private ProductRepository productRepository;

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataMongodbApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    repository.deleteAll();
    productRepository.deleteAll();

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

}
