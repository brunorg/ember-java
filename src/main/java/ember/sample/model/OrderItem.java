package ember.sample.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("items")
public class OrderItem extends BaseModel {

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("order")
    private Order order;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("product")
    private Product product;

    private Integer quantity;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // @JsonSetter
    // public void setOrder(BigInteger id) {
    //     this.order = new Order();
    //     this.order.setId(id);
    // }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // @JsonSetter
    // public void setProduct(BigInteger id) {
    //     this.product = new Product();
    //     this.product.setId(id);
    // }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
