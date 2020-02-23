import Route from "@ember/routing/route";

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
}
