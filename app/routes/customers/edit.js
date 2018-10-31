import Route from '@ember/routing/route';

export default Route.extend({
  model(params) {
    if (params.customer_id != "new") {
      return this.store.findRecord('customer', params.customer_id);
    } else {
      let customer = {
        "type": "customer",
        "id": null,
        "attributes": {
          "first-name": "First Name",
          "last-name": "Last Name",
        }
      }

      return customer;
    }
  },
  actions: {
    update(data) {
      this.store.createRecord('customer', data);
    }
  }
});
