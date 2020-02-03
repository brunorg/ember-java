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
import ember.sample.model.Product;
import ember.sample.repository.ProductRepository;

@RestController
class ProductController {

  @Autowired
  private ProductRepository repository;

  @GetMapping("/products")
  public byte[] all() throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Product.class);

    return converter.writeDocumentCollection(new JSONAPIDocument<List<Product>>(repository.findAll()));
  }

  @GetMapping("/products/{id}")
  public byte[] findById(@PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Product.class);
    return converter.writeDocument(
        new JSONAPIDocument<Product>(repository.findById(id).orElseThrow(ResourceNotFoundException::new)));
  }

  @PostMapping("/products")
  public byte[] add(@RequestBody byte[] document)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Product.class);
    converter.disableDeserializationOption(DeserializationFeature.REQUIRE_RESOURCE_ID);
    Product newProduct = repository.insert(converter.readDocument(document, Product.class).get());
    return converter.writeDocument(new JSONAPIDocument<Product>(newProduct));
  }

  @PutMapping("/products/{id}")
  public byte[] update(@RequestBody byte[] document, @PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Product.class);
    Product newProduct = repository.save(converter.readDocument(document, Product.class).get());
    return converter.writeDocument(new JSONAPIDocument<Product>(newProduct));
  }

  @PatchMapping("/products/{id}")
  public byte[] patch(@RequestBody byte[] document, @PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    ResourceConverter converter = new ResourceConverter(Product.class);
    Product Product = converter.readDocument(document, Product.class).get();
    Product newProduct = repository.save(Product);
    return converter.writeDocument(new JSONAPIDocument<Product>(newProduct));
  }

  @DeleteMapping("/products/{id}")
  public byte[] delete(@PathVariable String id) throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    repository.deleteById(id);
    ResourceConverter converter = new ResourceConverter(Product.class);
    return converter.writeDocument(new JSONAPIDocument<Product>());
  }

}
