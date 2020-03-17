import Route from "@ember/routing/route";
import { action } from '@ember/object';

export default class CustomersEditRoute extends Route {
  model(params) {
    if (params.customer_id == "new") {
      return {};
    }
    return this.store
      .findRecord("customer", params.customer_id)
      .catch(function() {
        return {};
      });
  }

  @action
  didTransition() {
    this.controllerFor("customers").set("collapsed", false);
  }
}
