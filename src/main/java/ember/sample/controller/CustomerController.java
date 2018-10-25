package ember.sample.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ember.sample.model.Customer;
import ember.sample.repository.CustomerRepository;

@RestController
class CustomerController {

  @Autowired
  private CustomerRepository repository;

  @GetMapping("/customers")
  public byte[] all() throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Customer.class);

    return converter.writeDocumentCollection(new JSONAPIDocument<List<Customer>>(repository.findAll()));
  }

}
