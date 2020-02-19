package ember.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.Order;
import ember.sample.model.OrderItem;

public interface OrderRepository extends MongoRepository<Order, String> {

}
