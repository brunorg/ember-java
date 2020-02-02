package ember.sample.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, BigInteger> {

  public Customer findByFirstName(String firstName);

  public List<Customer> findByLastName(String lastName);

}
