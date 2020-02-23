import Route from "@ember/routing/route";

export default class OrdersEditRoute extends Route {
  model(params) {
    if (params.order_id == "new") {
      return {};
    }
    return this.store.findRecord("order", params.order_id).catch(function() {
      return {};
    });
  }
}
