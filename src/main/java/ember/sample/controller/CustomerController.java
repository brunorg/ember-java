package ember.sample.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ember.sample.exception.ResourceNotFoundException;
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

  @GetMapping("/customers/{id}")
  public byte[] findById(@PathVariable String id) throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Customer.class);
    return converter.writeDocument(new JSONAPIDocument<Customer>(repository.findById(id).orElseThrow(ResourceNotFoundException::new)));
  }

  @PostMapping("/customers")
  public byte[] add(@RequestBody byte[] document) throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Customer.class);
    converter.disableDeserializationOption(DeserializationFeature.REQUIRE_RESOURCE_ID);
    Customer newCustomer = repository.insert(converter.readDocument(document, Customer.class).get());
    return converter.writeDocument(new JSONAPIDocument<Customer>(newCustomer));
  }

  @PutMapping("/customers/{id}")
  public byte[] update(@RequestBody byte[] document, @PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Customer.class);
    Customer newCustomer = repository.save(converter.readDocument(document, Customer.class).get());
    return converter.writeDocument(new JSONAPIDocument<Customer>(newCustomer));
  }

  @PatchMapping("/customers/{id}")
  public byte[] patch(@RequestBody byte[] document, @PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Customer.class);
    Customer customer = converter.readDocument(document, Customer.class).get();
    Customer newCustomer = repository.save(customer);
    return converter.writeDocument(new JSONAPIDocument<Customer>(newCustomer));
  }

  @DeleteMapping("/customers/{id}")
  public byte[] delete(@PathVariable String id) throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    repository.deleteById(id);
    ResourceConverter converter = new ResourceConverter(Customer.class);
    return converter.writeDocument(new JSONAPIDocument<Customer>());
  }

}
