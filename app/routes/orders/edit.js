import Route from '@ember/routing/route';

export default Route.extend({
  model(params) {
    if (params.order_id == "new") {
      return {};
    }
    return this.store.findRecord("order", params.order_id).catch(function() {
      return {};
    });
  },
  actions: {
    async update(order) {
      if (order.id) {
        order.save();
      } else {
        let newOrder = this.store.createRecord("order", order);
        order.items.fore
        await newOrder.save();

        let promises = order.items.map((item) => {
          item.set('order-item', parent);
          return item.save();
        })

        await Promise.all(promises);
      }
    },
    cancel(order) {
      if (order.id) {
        if (order.get("hasDirtyAttributes")) {
          order.rollbackAttributes();
        }
      }
      this.transitionTo("orders");
    }
  }
});
