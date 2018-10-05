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

import ember.sample.entity.Customer;
import ember.sample.manager.CustomerManager;

@Path("customers")
@RequestScoped
public class CustomerService {

    @Inject
    private CustomerManager manager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customers get(@QueryParam("ids[]") List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return new Customers(manager.findByIds(ids));
        }
        return new Customers(manager.findAll());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customer get(@PathParam("id") Long id) {
        return manager.find(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer save(Customer customer) {
        customer.setId(null);
        return manager.save(customer);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer update(@PathParam("id") Long id, Customer customer) {
        customer.setId(id);
        return manager.update(customer);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id) {
        manager.delete(id);
    }

    @JsonRootName("customers")
    class Customers extends ArrayList<Customer> {
        private static final long serialVersionUID = 1L;

        public Customers(Collection<? extends Customer> c) {
            addAll(c);
        }
    }
}
