require('ejr/core');

App.Router.map(function() {
  this.resource("customers");
  this.resource("customer", {
    path: '/customer/:customer_id'
  });
  this.resource("customer.new", {
    path: '/customer'
  });

  this.resource("products");
  this.resource("product", {
    path: '/product/:product_id'
  });
  this.resource("product.new", {
    path: '/product'
  });

  this.resource("orders");
  this.resource("order", {
    path: '/order/:order_id'
  });
  this.resource("order.new", {
    path: '/order'
  });
});

// ### 
// CUSTOMERS
App.CustomersRoute = Ember.Route.extend({
  model: function() {
    return App.Customer.find();
  }
});

App.CustomerNewRoute = Ember.Route.extend({
  setupController: function(controller, model) {
    this.controllerFor('customer').set('content', App.Customer.createRecord());
  },
  renderTemplate: function() {
    this.render('customer', {
      controller: 'customer'
    });
  }
});

// ### 
// PRODUCTS
App.ProductsRoute = Ember.Route.extend({
  model: function() {
    return App.Product.find();
  }
});

App.ProductNewRoute = Ember.Route.extend({
  setupController: function(controller, model) {
    this.controllerFor('product').set('content', App.Product.createRecord());
  },
  renderTemplate: function() {
    this.render('product', {
      controller: 'product'
    });
  }
});

// ### 
// ORDERS
App.OrdersRoute = Ember.Route.extend({
  model: function() {
    return App.Order.find();
  }
});

App.OrderNewRoute = Ember.Route.extend({
  setupController: function(controller, model) {
    this.controllerFor('order').set('content', App.Order.createRecord());
  },
  renderTemplate: function() {
    this.render('order', {
      controller: 'order'
    });
  }
});