package ember.sample.manager;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ember.sample.entity.CustomerOrder;
import ember.sample.entity.OrderItem;

public class OrderManager extends Manager<CustomerOrder, Long> {

    @Inject
    private ProductManager productManager;

    @Override
    public CustomerOrder save(CustomerOrder order) {
        order.setId(null);
        order.setCreationDate(LocalDateTime.now());
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        Set<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            item.setCustomerOrder(order);
            entityManager.persist(item);
        }
        entityManager.getTransaction().commit();
        return order;
    }

    public List<OrderItem> save(Long id, List<OrderItem> items) {
        CustomerOrder order = find(id);
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        for (OrderItem item : items) {
            item.setCustomerOrder(order);
            item.setProduct(productManager.find(item.getProduct().getId()));
            entityManager.persist(item);
        }
        entityManager.getTransaction().commit();
        return items;
    }

}
