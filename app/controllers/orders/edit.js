import Controller from '@ember/controller';

export default Controller.extend({
  actions: {
    filterCustomers(param) {
      if (param !== "") {
        return this.store.query("customer", { name: param });
      } else {
        return this.store.findAll("customer");
      }
    },
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
    },
    filterProducts(param) {
      if (param !== "") {
        return this.store.query("product", { name: param });
      } else {
        return this.store.findAll("product");
      }
    },
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
});
