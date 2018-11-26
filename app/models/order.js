import DS from 'ember-data';

export default DS.Model.extend({
  creationDate: DS.attr('date'),
  customer: DS.belongsTo('App.Customer'),
  items: DS.hasMany('App.OrderItem')
});
