package ember.sample.rest;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonRootName;

import ember.sample.entity.CustomerOrder;
import ember.sample.entity.OrderItem;
import ember.sample.manager.OrderItemManager;
import ember.sample.manager.OrderManager;

@Path("orders")
@RequestScoped
public class OrderService {

    @Inject
    private OrderManager manager;

    @Inject
    private OrderItemManager orderItemManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerOrders get(@QueryParam("ids[]") List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return new CustomerOrders(manager.findByIds(ids));
        }
        return new CustomerOrders(manager.findAll());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerOrder get(@PathParam("id") Long id) {
        return manager.find(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CustomerOrder save(CustomerOrder customerOrder) {
        return manager.save(customerOrder);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CustomerOrder update(@PathParam("id") Long id, CustomerOrder customerOrder) {
        customerOrder.setId(id);
        manager.update(customerOrder);
        return manager.find(id);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id) {
        manager.delete(id);
    }

    @JsonRootName("orders")
    class CustomerOrders extends ArrayList<CustomerOrder> {
        private static final long serialVersionUID = 1L;

        public CustomerOrders(Collection<? extends CustomerOrder> c) {
            addAll(c);
        }
    }

    @Path("/{id}/items")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItem> getItems(@PathParam("id") Long id) {
        return orderItemManager.findByOrderId(id);
    }

    @Path("/{id}/items")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<OrderItem> save(@PathParam("id") Long id, List<OrderItem> items) {
        return manager.save(id, items);
    }

}
