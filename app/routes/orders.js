import Route from "@ember/routing/route";
import { action } from "@ember/object";

export default class OrdersRoute extends Route {
  model() {
    return this.store.findAll("order", { include: "customer" });
  }

  @action
  remove(record) {
    return record.destroyRecord();
  }
}
