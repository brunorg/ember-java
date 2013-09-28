require('ejr/core');

App.ProductsController = Ember.ArrayController.extend({
  content: App.Product.find(),

  remove: function(model) {
    this.get('content').removeReference(model);
    model.deleteRecord();
    model.get('transaction').commit();
  }

});

App.ProductController = App.TransactionalController.extend({

  modelType: App.Product,

  didCreate: function(record) {
    App.Router.router.transitionTo('product', this.get('content'));
  }

});