import Route from "@ember/routing/route";
import { action } from "@ember/object";

export default class OrdersEditRoute extends Route {
  model(params) {
    let order;
    if (params.order_id == "new") {
      order = this.store.createRecord("order", {});
    } else {
      order = this.store.findRecord("order", params.order_id).catch(function() {
        return {};
      });
    }
    return order;
  }

  @action
  didTransition() {
    this.controllerFor("orders").set("collapsed", false);
  }
}
