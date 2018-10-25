package ember.sample.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, BigInteger> {

}
