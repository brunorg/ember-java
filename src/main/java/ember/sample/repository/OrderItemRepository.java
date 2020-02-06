package ember.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.OrderItem;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

}
