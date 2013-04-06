package ember.sample.manager;


import java.util.List;

import javax.persistence.Query;

import ember.sample.entity.OrderItem;

public class OrderItemManager extends Manager<OrderItem, Long> {

    public List<OrderItem> findByOrderId(Long id) {
        Query query = getEntityManager().createQuery("SELECT i FROM OrderItem i WHERE i.customerOrder.id = :id");
        query.setParameter("id", id);
        return findBy(query);
    }

}
