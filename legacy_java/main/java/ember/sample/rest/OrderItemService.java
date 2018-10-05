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

import ember.sample.entity.OrderItem;
import ember.sample.manager.OrderItemManager;

@Path("order_items")
@RequestScoped
public class OrderItemService {

    @Inject
    private OrderItemManager manager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItems get(@QueryParam("ids[]") List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return new OrderItems(manager.findByIds(ids));
        }
        return new OrderItems(manager.findAll());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItem get(@PathParam("id") Long id) {
        return manager.find(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItem save(OrderItem orderItem) {
        return manager.save(orderItem);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItem update(@PathParam("id") Long id, OrderItem orderItem) {
        orderItem.setId(id);
        return manager.update(orderItem);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id) {
        manager.delete(id);
    }

    @JsonRootName("order_items")
    class OrderItems extends ArrayList<OrderItem> {
        private static final long serialVersionUID = 1L;

        public OrderItems(Collection<? extends OrderItem> c) {
            addAll(c);
        }
    }

}
