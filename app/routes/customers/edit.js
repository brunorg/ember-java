import Route from '@ember/routing/route';

export default Route.extend({
  model(params) {
    if (params.customer_id == 'new') {
      return {};
    }
    return this.store.findRecord('customer', params.customer_id).catch(function () {
      return {};
    });
  },
  actions: {
    update(customer) {
      if (customer.id) {
        customer.save();
      } else {
        let newCustomer = this.store.createRecord('customer', customer);
        newCustomer.save();
      }
    },
    cancel(customer) {
      if (customer.id) {
        if (customer.get('hasDirtyAttributes')) {
          customer.rollbackAttributes();
        }
      }
      this.transitionTo('customers');
    }
  }
});
