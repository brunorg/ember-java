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

import ember.sample.entity.Product;
import ember.sample.manager.ProductManager;

@Path("products")
@RequestScoped
public class ProductService {

    @Inject
    private ProductManager manager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Products get(@QueryParam("ids[]") List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return new Products(manager.findByIds(ids));
        }
        return new Products(manager.findAll());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(@PathParam("id") Long id) {
        return manager.find(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Product save(Product product) {
        product.setId(null);
        return manager.save(product);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Product update(@PathParam("id") Long id, Product product) {
        product.setId(id);
        return manager.update(product);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id) {
        manager.delete(id);
    }

    @JsonRootName("products")
    class Products extends ArrayList<Product> {
        private static final long serialVersionUID = 1L;

        public Products(Collection<? extends Product> c) {
            addAll(c);
        }
    }

}
