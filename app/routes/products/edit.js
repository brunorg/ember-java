import Route from "@ember/routing/route";
import { action } from '@ember/object';

export default class ProductsEditRoute extends Route {
  model(params) {
    if (params.product_id == 'new') {
      return {};
    }
    return this.store.findRecord('product', params.product_id).catch(function () {
      return {};
    });
  }

  @action
  didTransition() {
    this.controllerFor("products").set("collapsed", false);
  }
}
