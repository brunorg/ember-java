import Controller from "@ember/controller";
import { action } from "@ember/object";
import { inject as service } from "@ember/service";

export default class CustomersEditController extends Controller {
  @service messaging;

  @action
  update(customer) {
    let messaging = this.messaging;
    if (customer.id) {
      customer.save().catch(function(reason) {
        messaging.addError(reason);
      });
    } else {
      let newCustomer = this.store.createRecord("customer", customer);
      newCustomer.save().catch(function(reason) {
        newCustomer.unloadRecord();
        messaging.addError(reason);
      });
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
