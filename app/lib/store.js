require('ejr/core');

App.store = DS.Store.create({
  revision: 12
});

DS.RESTAdapter.reopen({
  namespace: 'rest'
});

App.Customer = DS.Model.extend({
  firstName: DS.attr('string'),
  lastName: DS.attr('string'),
  orders: DS.hasMany('App.Order'),

  fullName: function() {
    return this.get('firstName') + ' ' + this.get('lastName');
  }.property('firstName', 'lastName')
});

App.Order = DS.Model.extend({
  creationDate: DS.attr('date'),
  customer: DS.belongsTo('App.Customer'),
  items: DS.hasMany('App.OrderItem'),

  totalItems: function() {
    return this.get('items').getEach('quantity').reduce(function(accum, item) {
      return parseInt(accum) + parseInt(item);
    }, 0);
  }.property('items.@each.quantity'),
  totalPrice: function() {
    return this.get('items').getEach('totalPrice').reduce(function(accum, item) {
      return accum + item;
    }, 0);
  }.property('items.@each.totalPrice')
});

App.OrderItem = DS.Model.extend({
  quantity: DS.attr('number'),
  order: DS.belongsTo('App.Order'),
  product: DS.belongsTo('App.Product'),

  totalPrice: function() {
    var productPrice = this.get('product.price');
    var qty = this.get('quantity');
    return productPrice * qty;
  }.property('quantity', 'product.price')
});

App.Product = DS.Model.extend({
  name: DS.attr('string'),
  description: DS.attr('string'),
  price: DS.attr('number')
});

App.Error = DS.Model.extend({
  type: DS.attr('string'),
  message: DS.attr('string'),
  invalidValue: DS.attr('string'),
  property: DS.attr('string'),
  className: DS.attr('string')
});