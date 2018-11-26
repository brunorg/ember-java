import Route from '@ember/routing/route';

export default Route.extend({
  model(params) {
    if (params.order_id == 'new') {
      return {};
    }
    return this.store.findRecord('order', params.order_id).catch(function () {
      return {};
    });
  }
});
