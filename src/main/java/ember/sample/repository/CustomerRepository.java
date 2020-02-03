package ember.sample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  public Optional<Customer> findByFirstName(String firstName);

  public List<Customer> findByLastName(String lastName);

}
