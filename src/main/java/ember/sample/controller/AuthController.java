package ember.sample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ember.sample.exception.ResourceNotFoundException;
import ember.sample.model.User;
import ember.sample.repository.UserRepository;

@RestController
@RequestMapping("/users")
class AuthController {

  @Autowired
  private UserRepository repository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/{email}")
  public byte[] findById(@PathVariable String email)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(User.class);
    User user = repository.findByUsername(email).orElseThrow(ResourceNotFoundException::new);
    return converter.writeDocument(new JSONAPIDocument<User>(user));
  }

  @PostMapping("/sign-up")
  public void signUp(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    repository.save(user);
  }

}
