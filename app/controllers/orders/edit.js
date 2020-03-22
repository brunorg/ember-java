import Controller from "@ember/controller";
import { action } from "@ember/object";
import { A } from "@ember/array";
import { inject as service } from "@ember/service";
import { tracked } from "@glimmer/tracking";

export default class OrdersEditController extends Controller {
  @service messaging;
  @tracked results = A([]);
  @tracked loadingResults = false;
  @tracked showModal = false;
  @tracked modalTitle;
  @tracked isCustomerModal;

  @action
  onCreate(modal) {
    this.isCustomerModal = (modal === "customerModal" ? true : false);
    this.results.clear();
    this.showModal = true;
    this.loadingResults = true;
    let promise;

    if (modal === "customerModal") {
      this.modalTitle = "Select Customer";
      promise = this.filterCustomers();
    }

    if (modal === "productModal") {
      this.modalTitle = "Select Product";
      promise = this.filterProducts();
    }

    promise.then(results => {
        this.results.addObjects(results);
        this.loadingResults = false;
    });
  }

  @action
  async update(order) {
    let messaging = this.messaging;
    if (order.id) {
      order.save().catch(function (reason) {
        messaging.addError(reason);
      });
    } else {
      order.save().then(() => {
        let promises = order.items.map(item => {
          item.set("order-item", parent);
          return item.save().catch(function (reason) {
            item.unloadRecord();
            messaging.addError(reason);
          });
        });

        Promise.all(promises).catch(function (reason) {
          messaging.addError(reason);
        });
      }).catch(function (reason) {
        order.unloadRecord();
        messaging.addError(reason);
      });
    }
  }

  @action
  cancel(order) {
    if (order.id) {
      if (order.get("hasDirtyAttributes")) {
        order.rollbackAttributes();
      }
    }
    this.transitionToRoute("orders");
  }

  @action
  filterCustomers(param) {
    if (param && param !== "") {
      return this.store.query("customer", { name: param });
    } else {
      return this.store.findAll("customer");
    }
  }

  @action
  selectCustomer(customer) {
    this.model.customer = customer;
    this.model.hasDirtyAttributes = true;
  }

  @action
  filterProducts(param) {
    if (param !== "") {
      return this.store.query("product", { name: param });
    } else {
      return this.store.findAll("product");
    }
  }

  @action
  selectProduct(product) {
    var item = this.store.createRecord("order-item", {
      product: product,
      quantity: 0
    });
    let items = this.get("model.items");
    if (items) {
      items.pushObject(item);
    } else {
      items = A([item]);
      this.set("model.items", items);
    }
    this.model.hasDirtyAttributes = true;
  }

  @action
  deleteItem(item) {
    let items = this.get("model.items");
    if (items) {
      items.removeObject(item);
    }
    this.model.hasDirtyAttributes = true;
  }

}
