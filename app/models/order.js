import DS from 'ember-data';
import { computed } from "@ember/object";

export default DS.Model.extend({
  creationDate: DS.attr("date"),
  customer: DS.belongsTo("customer"),
  items: DS.hasMany("order-item"),
  totalQuantity: computed("items.@each.quantity", function() {
    return this.get("items").reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.quantity, 10);
    }, 0);
  }),
  totalPrice: computed("items.@each.totalPrice", function() {
    return this.get("items").reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.totalPrice, 10);
    }, 0);
  })
});
