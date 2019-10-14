import DS from 'ember-data';
import { computed } from "@ember/object";

export default DS.Model.extend({
  order: DS.belongsTo("order"),
  product: DS.belongsTo("product"),
  quantity: DS.attr(),
  totalPrice: computed("product", "quantity", function() {
    return this.get("product.price") * this.get("quantity");
  })
});
