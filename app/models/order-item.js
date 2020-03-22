import classic from 'ember-classic-decorator';
import { computed } from '@ember/object';
import Model, { belongsTo, attr } from '@ember-data/model';

@classic
export default class OrderItem extends Model {
  @belongsTo("order")
  order;

  @belongsTo("product")
  product;

  @attr()
  quantity;

  @computed("product.price", "quantity")
  get totalPrice() {
    return this.get("product.price") * this.quantity;
  }
}
