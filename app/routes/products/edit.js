import Route from '@ember/routing/route';

export default Route.extend({
  model(params) {
    if (params.customer_id == 'new') {
      return {};
    }
    return this.store.findRecord('product', params.product_id).catch(function () {
      return {};
    });
  },
  actions: {
    update(product) {
      if (product.id) {
        product.save();
      } else {
        let newProduct = this.store.createRecord('product', product);
        newProduct.save();
      }
    },
    cancel(product) {
      if (product.id) {
        if (product.get('hasDirtyAttributes')) {
          product.rollbackAttributes();
        }
      }
      this.transitionTo('products');
    }
  }
});
