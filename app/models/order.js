import Model, { attr, belongsTo, hasMany } from "@ember-data/model";

export default class OrderModel extends Model {
  @attr("date") creationDate;
  @belongsTo("customer") customer;
  @hasMany("order-item") items;

  get fullName() {
    return `${this.customer.firstName} ${this.customer.lastName}`;
  }

  get totalQuantity() {
    return this.items.reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.quantity, 10);
    }, 0);
  }

  get totalPrice() {
    return this.items.reduce((sum, item) => {
      return parseInt(sum, 10) + parseInt(item.totalPrice, 10);
    }, 0);
  }
}
