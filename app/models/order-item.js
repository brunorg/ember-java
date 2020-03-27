import { computed } from '@ember/object';
import Model, { belongsTo, attr } from '@ember-data/model';

export default class OrderItemModel extends Model {
  @belongsTo("order") order;
  @belongsTo("product") product;
  @attr() quantity;

  @computed("product.price", "quantity")
  get totalPrice() {
    return this.get("product.price") * this.quantity;
  }
}
