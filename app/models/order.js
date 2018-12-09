import DS from 'ember-data';

export default DS.Model.extend({
  creationDate: DS.attr('date'),
  customer: DS.belongsTo('customer'),
  items: DS.hasMany('order-item')
});
