import Controller from "@ember/controller";
import { action } from "@ember/object";

export default class ProductsEditController extends Controller {
  @action
  update(product) {
    if (product.id) {
      product.save();
    } else {
      let newProduct = this.store.createRecord("product", product);
      newProduct.save();
    }
  }

  @action
  cancel(product) {
    if (product.id) {
      if (product.get("hasDirtyAttributes")) {
        product.rollbackAttributes();
      }
    }
    this.transitionTo("products");
  }
}
