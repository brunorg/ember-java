package ember.sample.model;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonRootName("customer")
public class Customer {

    @Id
    private Long id;

    @Size(max = 24)
    @NotEmpty
    @JsonProperty("first_name")
    private String firstName;

    @Size(max = 24)
    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("order_ids")
    private Set<Order> orders = new TreeSet<>();

    public Customer() {
    }
    
    public Customer(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}


