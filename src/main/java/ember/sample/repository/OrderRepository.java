package ember.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
