import Controller from "@ember/controller";
import { action } from "@ember/object";

export default class OrdersEditController extends Controller {
  @action
  async update(order) {
    if (order.id) {
      order.save();
    } else {
      let newOrder = this.store.createRecord("order", order);
      order.items.fore;
      await newOrder.save();

      let promises = order.items.map(item => {
        item.set("order-item", parent);
        return item.save();
      });

      await Promise.all(promises);
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
    if (param !== "") {
      return this.store.query("customer", { name: param });
    } else {
      return this.store.findAll("customer");
    }
  }

  @action
  selectCustomer(customer) {
    this.set("model.customer", customer);
    //order.set("customer", customer);
    // if (order.id) {
    //   order.set('customer', customer);
    //   order.save();
    // } else {
    //   let newOrder = this.store.createRecord('order', order);
    //   newOrder.set('customer', customer);
    //   newOrder.save();
    // }
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
      var newItems = items.toArray();
      newItems.push(item);
      this.set("model.items", newItems);
    } else {
      items = [item];
      this.set("model.items", items);
    }
  }
}
