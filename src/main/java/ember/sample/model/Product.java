package ember.sample.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("products")
public class Product extends BaseModel {

    @Size(max = 24)
    @NotEmpty
    private String name;

    @Size(max = 100)
    @NotEmpty
    private String description;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

  @Override
  public String toString() {
    return String.format("Produtc [id=%s, name='%s', description='%s', price='%s']", id, name, description, price);
  }

}
