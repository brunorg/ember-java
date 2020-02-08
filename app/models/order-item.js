import Model, { belongsTo, attr } from '@ember-data/model';
import { computed } from "@ember/object";

export default Model.extend({
  order: belongsTo("order"),
  product: belongsTo("product"),
  quantity: attr(),
  totalPrice: computed("product", "quantity", function() {
    return this.get("product.price") * this.quantity;
  })
});
