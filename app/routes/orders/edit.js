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
    update(order) {
      if (order.id) {
        order.save();
      } else {
        let newOrder = this.store.createRecord("order", order);
        newOrder.save();
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
