import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { action } from "@ember/object";

export default class ProductsRoute extends Route {
  @service messaging;

  model() {
    let messaging = this.messaging;
    return this.store.findAll("product").catch((reason) => {
      messaging.addError(reason);
      return [];
    });
  }

  @action
  didTransition() {
    this.controllerFor("products").set("collapsed", true);
  }
}
