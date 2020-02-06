package ember.sample.controller;

import java.time.LocalDateTime;
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
import ember.sample.model.Order;
import ember.sample.model.OrderItem;
import ember.sample.repository.CustomerRepository;
import ember.sample.repository.OrderItemRepository;
import ember.sample.repository.OrderRepository;

@RestController
class OrderController {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ResourceConverter converter;

  @GetMapping("/orders")
  public byte[] all() throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    List<Order> findAll = repository.findAll();
    findAll.forEach(order -> { order.setCustomer( customerRepository.findById(order.getCustomer().getId()).get() ); });
    return converter.writeDocumentCollection(new JSONAPIDocument<List<Order>>(findAll));
  }

  @GetMapping("/orders/{id}")
  public byte[] findById(@PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    return converter.writeDocument(new JSONAPIDocument<Order>(repository.findById(id).orElseThrow(ResourceNotFoundException::new)));
  }

  @PostMapping("/orders")
  public byte[] add(@RequestBody byte[] document)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    converter.disableDeserializationOption(DeserializationFeature.REQUIRE_RESOURCE_ID);
    Order order = converter.readDocument(document, Order.class).get();
    order.setCreationDate(LocalDateTime.now());
    Order newOrder = repository.insert(order);
    return converter.writeDocument(new JSONAPIDocument<Order>(newOrder));
  }

  @PutMapping("/orders/{id}")
  public byte[] update(@RequestBody byte[] document, @PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    Order newOrder = repository.save(converter.readDocument(document, Order.class).get());
    return converter.writeDocument(new JSONAPIDocument<Order>(newOrder));
  }

  @PatchMapping("/orders/{id}")
  public byte[] patch(@RequestBody byte[] document, @PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    Order Order = converter.readDocument(document, Order.class).get();
    Order newOrder = repository.save(Order);
    return converter.writeDocument(new JSONAPIDocument<Order>(newOrder));
  }

  @DeleteMapping("/orders/{id}")
  public byte[] delete(@PathVariable String id) throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    repository.deleteById(id);
    return converter.writeDocument(new JSONAPIDocument<Order>());
  }

  @GetMapping("/order-items/{id}")
  public byte[] findItemById(@PathVariable String id)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    return converter.writeDocument(
        new JSONAPIDocument<OrderItem>(orderItemRepository.findById(id).orElseThrow(ResourceNotFoundException::new)));
  }

  @PostMapping("/order-items")
  public byte[] addItem(@RequestBody byte[] document)
      throws JsonProcessingException, IllegalAccessException, DocumentSerializationException {
    converter.disableDeserializationOption(DeserializationFeature.REQUIRE_RESOURCE_ID);
    OrderItem orderItem = converter.readDocument(document, OrderItem.class).get();
    Order order = repository.findById(orderItem.getOrder().getId()).get();

    OrderItem newOrderItem = orderItemRepository.insert(orderItem);
    order.getItems().add(newOrderItem);
    repository.save(order);

    return converter.writeDocument(new JSONAPIDocument<OrderItem>(newOrderItem));
  }

}
