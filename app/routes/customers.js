import Route from "@ember/routing/route";

export default class CustomersRoute extends Route {
  model() {
    return this.store.findAll("customer");
  }
}
