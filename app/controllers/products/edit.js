import Controller from "@ember/controller";
import { action } from "@ember/object";
import { inject as service } from "@ember/service";

export default class ProductsEditController extends Controller {
  @service messaging;

  @action
  update(product) {
    let messaging = this.messaging;
    if (product.id) {
      product.save().catch(function(reason) {
        messaging.addError(reason);
      });
    } else {
      let newProduct = this.store.createRecord("product", product);
      newProduct.save().catch(function(reason) {
        newProduct.unloadRecord();
        messaging.addError(reason);
      });
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

}
