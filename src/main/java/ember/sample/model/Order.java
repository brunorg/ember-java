package ember.sample.model;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonRootName("order")
public class Order {

    @Id
    private Long id;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("customer_id")
    private Customer customer;

    @JsonProperty("creation_date")
    private Calendar creationDate;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("item_ids")
    private Set<OrderItem> items = new TreeSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonSetter
    public void setCustomer(Long id) {
        this.customer = new Customer(id);
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
