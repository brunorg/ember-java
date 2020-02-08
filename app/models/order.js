import Model, { attr, belongsTo, hasMany } from '@ember-data/model';
import { computed } from "@ember/object";

export default Model.extend({
  creationDate: attr("date"),
  customer: belongsTo("customer"),
  items: hasMany("order-item"),
  totalQuantity: computed("items.@each.quantity", function() {
    return this.items.reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.quantity, 10);
    }, 0);
  }),
  totalPrice: computed("items.@each.totalPrice", function() {
    return this.items.reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.totalPrice, 10);
    }, 0);
  })
});
