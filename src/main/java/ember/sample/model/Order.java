package ember.sample.model;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("orders")
public class Order extends BaseModel {

    // @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("creation-date")
    private Calendar creationDate;

    // @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("items")
    private Set<OrderItem> items = new TreeSet<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

}
