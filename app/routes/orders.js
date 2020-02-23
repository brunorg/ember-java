import Route from "@ember/routing/route";

export default class OrdersRoute extends Route {
  model() {
    return this.store.findAll("order", { include: "customer" });
  }
}
