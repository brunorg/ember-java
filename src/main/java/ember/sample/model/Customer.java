package ember.sample.model;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("customers")
public class Customer extends BaseModel {

    @Size(max = 24)
    @NotEmpty
    @JsonProperty("first-name")
    private String firstName;

    @Size(max = 24)
    @NotEmpty
    @JsonProperty("last-name")
    private String lastName;

    @JsonIdentityReference(alwaysAsId = true)
    // @JsonProperty("order-ids")
    @Relationship("orders")
    private Set<Order> orders = new TreeSet<>();

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // public Set<Order> getOrders() {
    //     return orders;
    // }

    // public void setOrders(Set<Order> orders) {
    //     this.orders = orders;
    // }

}


