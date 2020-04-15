package ember.sample.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ember.sample.model.User;

public interface UserRepository extends MongoRepository<User, String> {

  public Optional<User> findByUsername(String username);

}
