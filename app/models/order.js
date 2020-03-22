import classic from 'ember-classic-decorator';
import { computed } from '@ember/object';
import Model, { attr, belongsTo, hasMany } from '@ember-data/model';

@classic
export default class Order extends Model {
  @attr("date")
  creationDate;

  @belongsTo("customer")
  customer;

  @hasMany("order-item")
  items;

  @computed("items.@each.quantity")
  get totalQuantity() {
    return this.items.reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.quantity, 10);
    }, 0);
  }

  @computed("items.@each.{totalPrice}")
  get totalPrice() {
    return this.items.reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.totalPrice, 10);
    }, 0);
  }
}
