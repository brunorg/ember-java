package ember.sample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

  public Optional<Product> findByName(String name);

  public List<Product> findByDescriptionContaining(String description);

}
