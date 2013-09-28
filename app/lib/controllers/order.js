require('ejr/core');

App.OrdersController = Ember.ArrayController.extend({
  content: App.Order.find(),

  remove: function(model) {
    this.get('content').removeReference(model);
    model.deleteRecord();
    model.get('transaction').commit();
  }

});

App.OrderController = App.TransactionalController.extend({
  needs: ["customers", "products"],
  modelType: App.Order,
  isCustomersListEnabled: false,
  isProductsListEnabled: false,

  showCustomers: function() {
    var isEnabled = this.get('isCustomersListEnabled');
    this.set('isCustomersListEnabled', !isEnabled);
  },

  selectCustomer: function(customer) {
    var order = this.get('content'),
      transaction = order.get('transaction');

    transaction.add(customer);
    this.get('content').set('customer', customer);
    this.set('isCustomersListEnabled', false);
  },

  showProducts: function() {
    var isEnabled = this.get('isProductsListEnabled');
    this.set('isProductsListEnabled', !isEnabled);
  },

  addItem: function(product) {
    var order = this.get('content'),
      orderItems = order.get('items'),
      transaction = order.get('transaction');

    order.get('stateManager').goToState('updated');

    orderItems.createRecord({
      product: product,
      quantity: 0
    }, transaction);
  },

  deleteItem: function(item) {
    var order = this.get('content'),
      orderItems = order.get('items'),
      transaction = order.get('transaction');

    transaction.add(item);
    item.deleteRecord();

    var storedItem = App.store.referenceForClientId(item.clientId);
    orderItems.removeReference(storedItem);

  },

  selectProduct: function(product) {
    this.addItem(product);
    this.set('isProductsListEnabled', false);
  },

  didCreate: function(record) {
    App.Router.router.transitionTo('order', this.get('content'));
  }

});