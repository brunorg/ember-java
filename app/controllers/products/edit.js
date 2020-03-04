import Controller from "@ember/controller";
import { action } from "@ember/object";
import { inject as service } from "@ember/service";

export default class ProductsEditController extends Controller {
  @service messaging;

  @action
  update(product) {
    if (product.id) {
      product.save();
    } else {
      let newProduct = this.store.createRecord("product", product);
      newProduct.save().catch(this.error);
    }
  }

  @action
  cancel(product) {
    if (product.id) {
      if (product.get("hasDirtyAttributes")) {
        product.rollbackAttributes();
      }
    }
    this.transitionToRoute("products");
  }

  @action
  error(error) {
    error.errors.forEach(err => {
      this.messaging.addError(err.title);
    });
    this.transitionToRoute("products");
    this.transitionToRoute("application");
  }

}
