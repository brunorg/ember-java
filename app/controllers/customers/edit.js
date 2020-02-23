import Controller from "@ember/controller";
import { action } from "@ember/object";

export default class CustomersEditController extends Controller {
  @action
  update(customer) {
    if (customer.id) {
      customer.save();
    } else {
      let newCustomer = this.store.createRecord("customer", customer);
      newCustomer.save();
    }
  }

  @action
  cancel(customer) {
    if (customer.id) {
      if (customer.get("hasDirtyAttributes")) {
        customer.rollbackAttributes();
      }
    }
    this.transitionTo("customers");
  }
}
