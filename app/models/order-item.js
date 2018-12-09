import DS from 'ember-data';

export default DS.Model.extend({
  order: DS.belongsTo('order')
});
